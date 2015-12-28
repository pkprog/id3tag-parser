package pk.mp3.id3v2.frame;

import java.nio.charset.Charset;

/**
 * Created by pskhizhnyakov on 24.12.2015.
 */
public interface TextFrame extends Frame {
    StringBuilder getText();
    Charset getCharset();
}
