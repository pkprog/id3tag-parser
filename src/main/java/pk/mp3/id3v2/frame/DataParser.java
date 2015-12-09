package pk.mp3.id3v2.frame;

import java.nio.charset.Charset;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public interface DataParser {
    Charset getCharset();
    int getDefaultTerminatedString();
    byte[] getPureData();
}
