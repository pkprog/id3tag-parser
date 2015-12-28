package pk.mp3.id3v2.parser;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Created by pskhizhnyakov on 08.12.2015.
 */
public class TextDataParser230 implements TextDataParser {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.ISO_8859_1;
    private static final Charset UNIDODE_BIG_ENDIAN = StandardCharsets.UTF_16BE;
    private static final Charset UNIDODE_LITTLE_ENDIAN = StandardCharsets.UTF_16LE;

    private static final int DEFAULT_NEW_LINE = 0x0a;

    public Charset getCharset(byte[] data) {
        if (data == null) return DEFAULT_CHARSET;

        if (data.length == 1) return DEFAULT_CHARSET;

        if (data.length == 2) {
            byte byte1 = data[0], byte2 = data[1];

            if ((byte1 & 0xff) == 0xfe && (byte2 & 0xff) == 0xff) { //UNICODE big endian
                return UNIDODE_BIG_ENDIAN;
            }
            if ((byte1 & 0xff) == 0xff && (byte2 & 0xff) == 0xfe) { //UNICODE little endian
                return UNIDODE_LITTLE_ENDIAN;
            }

            return DEFAULT_CHARSET;
        } else {
            byte byte1 = data[0], byte2 = data[1], byte3 = data[2];

            if (getDifferentEncodingFlag(data).equals(DifferentEncodingFlag.ISO_8859_1)) {
                return DEFAULT_CHARSET;
            }

            if (getDifferentEncodingFlag(data).equals(DifferentEncodingFlag.NONE)) {
                if ((byte1 & 0xff) == 0xfe && (byte2 & 0xff) == 0xff) { //UNICODE big endian
                    return UNIDODE_BIG_ENDIAN;
                }
                if ((byte1 & 0xff) == 0xff && (byte2 & 0xff) == 0xfe) { //UNICODE little endian
                    return UNIDODE_LITTLE_ENDIAN;
                }
            }

            if (getDifferentEncodingFlag(data).equals(DifferentEncodingFlag.UNICODE)) {
                if ((byte2 & 0xff) == 0xfe && (byte3 & 0xff) == 0xff) { //UNICODE big endian
                    return UNIDODE_BIG_ENDIAN;
                }

                if ((byte2 & 0xff) == 0xff && (byte3 & 0xff) == 0xfe) { //UNICODE little endian
                    return UNIDODE_LITTLE_ENDIAN;
                }
            }

            return DEFAULT_CHARSET;
        }
/*
        if (data != null && data.length >= 3) {
            byte byte1 = data[0], byte2 = data[1], byte3 = data[2];

            if (getDifferentEncodingFlag(data).equals(DifferentEncodingFlag.ISO_8859_1)) {
                if ((byte2 & 0xff) >= 0x20 && (byte2 & 0xff) <= 0xff || (byte2 & 0xff) == DEFAULT_NEW_LINE) {
                    return DEFAULT_CHARSET;
                }
            } else if (getDifferentEncodingFlag(data).equals(DifferentEncodingFlag.UNICODE)) {
                if ((byte2 & 0xff) == 0xfe && (byte3 & 0xff) == 0xff) { //UNICODE big endian
                    return UNIDODE_BIG_ENDIAN;
                }

                if ((byte2 & 0xff) == 0xff && (byte3 & 0xff) == 0xfe) { //UNICODE little endian
                    return UNIDODE_LITTLE_ENDIAN;
                }
            } else {
                if ((byte1 & 0xff) == 0xff && (byte2 & 0xff) == 0xfe) { //UNICODE big endian
                    return UNIDODE_BIG_ENDIAN;
                }

                if ((byte1 & 0xff) == 0xfe && (byte2 & 0xff) == 0xff) { //UNICODE little endian
                    return UNIDODE_LITTLE_ENDIAN;
                }

                if ((byte1 & 0xff) >= 0x20 && (byte1 & 0xff) <= 0xff || (byte1 & 0xff) == DEFAULT_NEW_LINE || (byte1 & 0xff) == 0x00) {
                    return DEFAULT_CHARSET;
                }
            }
        }
*/
//        return DEFAULT_CHARSET;
    }

    public DifferentEncodingFlag getDifferentEncodingFlag(byte[] data) {
        if (data == null || data.length == 0) return DifferentEncodingFlag.NONE;

        byte byte1 = data[0];
        if ((byte1 & 0xff) == 0) { //Frames that allow different types of text encoding for ISO-8859-1
            return DifferentEncodingFlag.ISO_8859_1;
        } else
        if ((byte1 & 0xff) == 1) { //Frames that allow different types of text encoding for Unicode
            return DifferentEncodingFlag.UNICODE;
        }
        return DifferentEncodingFlag.NONE;
    }

    public TerminatingString getTerminatingString(byte[] data) {
        Charset charset = getCharset(data);

        if (charset.equals(UNIDODE_BIG_ENDIAN)) {
            return TerminatingString.UNICODE;
        }
        if (charset.equals(UNIDODE_LITTLE_ENDIAN)) {
            return TerminatingString.UNICODE;
        }
        if (charset.equals(DEFAULT_CHARSET)) {
            return TerminatingString.DEFAULT;
        }
/*
        if (data != null && data.length >= 2) {
            byte byte1 = data[0], byte2 = data[1];

            if ((byte1 & 0xff) == 0xff && (byte2 & 0xff) == 0xfe) { //UNICODE big endian
                return TerminatingString.UNICODE;
            }

            if ((byte1 & 0xff) == 0xfe && (byte2 & 0xff) == 0xff) { //UNICODE little endian
                return TerminatingString.UNICODE;
            }

            if ((byte1 & 0xff) >= 0x20 && (byte1 & 0xff) <= 0xff || (byte1 & 0xff) == DEFAULT_NEW_LINE || (byte1 & 0xff) == 0x00) {
                return TerminatingString.DEFAULT;
            }
        }
*/

        return TerminatingString.DEFAULT;
    }

    public byte[] getPureData(byte[] data) {
        if (data == null || data.length == 0) return new byte[0];

        if (getDifferentEncodingFlag(data).equals(DifferentEncodingFlag.NONE)) {
            if (getCharset(data).equals(DEFAULT_CHARSET)) {
                return data;
            } else if (getCharset(data).equals(UNIDODE_BIG_ENDIAN)) {
                return Arrays.copyOfRange(data, 2, data.length);
            } else if (getCharset(data).equals(UNIDODE_LITTLE_ENDIAN)) {
                return Arrays.copyOfRange(data, 2, data.length);
            } else {
                return data;
            }
        } else if (getDifferentEncodingFlag(data).equals(DifferentEncodingFlag.ISO_8859_1)) {
            return Arrays.copyOfRange(data, 1, data.length);
        } else if (getDifferentEncodingFlag(data).equals(DifferentEncodingFlag.UNICODE)) {
            if (getCharset(data).equals(UNIDODE_BIG_ENDIAN)) {
                return Arrays.copyOfRange(data, 3, data.length);
            } else if (getCharset(data).equals(UNIDODE_LITTLE_ENDIAN)) {
                return Arrays.copyOfRange(data, 3, data.length);
            }
        }
        return data;
    }

    @Override
    public CharBuffer getCharacters(byte[] data) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(getPureData(data));
        return getCharset(data).decode(byteBuffer);
    }

}
