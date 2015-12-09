package pk.mp3.id3v2.frame;

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

    private byte[] data;

    public DataParser230(byte[] data) {
        this.data = data;
    }

    public Charset getCharset() {
        if (data != null && data.length >= 2) {
            byte byte1 = data[0], byte2 = data[1];

            if ((byte1 & 0xff) >= 0x20 && (byte1 & 0xff) <= 0xff || (byte1 & 0xff) == DEFAULT_NEW_LINE || (byte1 & 0xff) == 0x00) {
                return DEFAULT_CHARSET;
            }

            if ((byte1 & 0xff) == 0xff && (byte2 & 0xff) == 0xfe) { //UNICODE big endian
                return UNIDODE_BIG_ENDIAN;
            }

            if ((byte1 & 0xff) == 0xfe && (byte2 & 0xff) == 0xff) { //UNICODE little endian
                return UNIDODE_LITTLE_ENDIAN;
            }
        }

        return DEFAULT_CHARSET;
    }

    public int getDefaultTerminatedString() {
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

    private byte[] getData() {
        return data;
    }

    public byte[] getPureData() {
        if (getCharset().equals(DEFAULT_CHARSET)) {
            if ((data[0] & 0xff) == 0x00) {
                return Arrays.copyOfRange(getData(), 1, getData().length);
            } else {
                return data;
            }
        } else if (getCharset().equals(UNIDODE_BIG_ENDIAN)) {
            return Arrays.copyOfRange(getData(), 3, getData().length);
        } else if (getCharset().equals(UNIDODE_LITTLE_ENDIAN)) {
            return Arrays.copyOfRange(getData(), 3, getData().length);
        } else {
            return data;
        }
    }

    public String toString() {
        return new String(getPureData(), getCharset());
    }

}
