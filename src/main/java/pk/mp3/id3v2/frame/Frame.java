package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.exception.UseOfMethodNotApplicable;
import pk.mp3.id3v2.frame.frametype.FrameType;

/**
 * Created by pskhizhnyakov on 07.12.2015.
 */
public interface Frame {
    FrameType getType();
    String getIdentifier();
    int getSize();
    byte[] getPureData() throws UseOfMethodNotApplicable;
    byte[] getSourceData();
//    Charset getCharset() throws UseOfMethodNotApplicable;
}
