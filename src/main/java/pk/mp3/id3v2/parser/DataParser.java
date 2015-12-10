package pk.mp3.id3v2.parser;

import java.nio.charset.Charset;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public interface DataParser {
    Charset getCharset(byte[] data);
    int getDefaultTerminatedString(byte[] data);
    DifferentEncodingFlag getDifferentEncodingFlag(byte[] data);
    byte[] getPureData(byte[] data);
}
