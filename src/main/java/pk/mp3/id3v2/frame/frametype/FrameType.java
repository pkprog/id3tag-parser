package pk.mp3.id3v2.frame.frametype;

import pk.mp3.id3v2.frame.Frame;
import pk.mp3.id3v2.frame.FrameSource;

/**
 * Created by pskhizhnyakov on 07.12.2015.
 */
public interface FrameType {
    boolean isMyId(String id);
    Frame createFrame(FrameSource frameSource);

    boolean isText();
    boolean isPicture();
    String getDescription();
}
