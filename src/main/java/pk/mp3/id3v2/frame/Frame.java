package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.frame.frametype.FrameType;

/**
 * Created by pskhizhnyakov on 07.12.2015.
 */
public interface Frame {
    FrameType getType();
    String getIdentifier();
//    int getSize();
    byte[] getSourceData();
    FrameSource getFrameSource();
//    Charset getCharset() throws UseOfMethodNotApplicable;
    byte[] getBytes();
}
