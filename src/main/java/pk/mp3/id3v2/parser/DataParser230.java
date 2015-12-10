package pk.mp3.id3v2.parser;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Created by pskhizhnyakov on 08.12.2015.
 */
public class DataParser230 implements DataParser {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.ISO_8859_1;
    private static final Charset UNIDODE_BIG_ENDIAN = StandardCharsets.UTF_16BE;
    private static final Charset UNIDODE_LITTLE_ENDIAN = StandardCharsets.UTF_16LE;

    private static final int DEFAULT_TERMINATED_STRING = 0x00;
    private static final int UNICODE_TERMINATED_STRING = 0x0000;

    private static final int DEFAULT_NEW_LINE = 0x0a;

    public Charset getCharset(byte[] data) {
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
                if ((byte1 & 0xff) >= 0x20 && (byte1 & 0xff) <= 0xff || (byte1 & 0xff) == DEFAULT_NEW_LINE || (byte1 & 0xff) == 0x00) {
                    return DEFAULT_CHARSET;
                }

                if ((byte1 & 0xff) == 1 && (byte2 & 0xfe) == 1) { //UNICODE big endian
                    return UNIDODE_BIG_ENDIAN;
                }

                if ((byte1 & 0xfe) == 1 && (byte2 & 0xff) == 1) { //UNICODE little endian
                    return UNIDODE_LITTLE_ENDIAN;
                }
            }
        }

        return DEFAULT_CHARSET;
    }

    public DifferentEncodingFlag getDifferentEncodingFlag(byte[] data) {
        byte byte1 = data[0];
        if ((byte1 & 0xff) == 0) { //Frames that allow different types of text encoding for ISO-8859-1
            return DifferentEncodingFlag.ISO_8859_1;
        } else
        if ((byte1 & 0xff) == 1) { //Frames that allow different types of text encoding for Unicode
            return DifferentEncodingFlag.UNICODE;
        }
        return DifferentEncodingFlag.NONE;
    }

    public int getDefaultTerminatedString(byte[] data) {
        if (data != null && data.length >= 2) {
            byte byte1 = data[0], byte2 = data[1];

            if ((byte1 & 0xff) >= 0x20 && (byte1 & 0xff) <= 0xff || (byte1 & 0xff) == DEFAULT_NEW_LINE || (byte1 & 0xff) == 0x00) {
                return DEFAULT_TERMINATED_STRING;
            }

            if ((byte1 & 0xff) == 0xff && (byte2 & 0xff) == 0xfe) { //UNICODE big endian
                return UNICODE_TERMINATED_STRING;
            }

            if ((byte1 & 0xff) == 0xfe && (byte2 & 0xff) == 0xff) { //UNICODE little endian
                return UNICODE_TERMINATED_STRING;
            }
        }

        return DEFAULT_TERMINATED_STRING;
    }

    public byte[] getPureData(byte[] data) {
        if (data == null || data.length == 0) return new byte[0];

        if (getDifferentEncodingFlag(data).equals(DifferentEncodingFlag.NONE)) {
            if (getCharset(data).equals(DEFAULT_CHARSET)) {
//                if ((data[0] & 0xff) == 0x00) {
//                    return Arrays.copyOfRange(data, 1, data.length);
//                } else {
//                    return data;
//                }
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

}
