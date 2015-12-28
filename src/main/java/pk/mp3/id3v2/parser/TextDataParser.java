package pk.mp3.id3v2.parser;

import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public interface TextDataParser {
    Charset getCharset(byte[] data);
    byte[] getPureData(byte[] data);
    CharBuffer getCharacters(byte[] data);

    int getDefaultTerminatedString(byte[] data);
    DifferentEncodingFlag getDifferentEncodingFlag(byte[] data);
}
