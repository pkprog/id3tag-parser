package pk.mp3.id3v2.frame.frametype;

import pk.mp3.id3v2.frame.Frame;
import pk.mp3.id3v2.frame.FrameSource;
import pk.mp3.id3v2.frame.FrameUnknown;

/**
 * Created by pskhizhnyakov on 10.12.2015.
 */
public class FrameTypeUnknown implements FrameType {
    @Override
    public boolean isMyId(String id) {
        return false;
    }

    @Override
    public Frame createFrame(FrameSource frameSource) {
        return new FrameUnknown(this, frameSource);
    }
    @Override
    public String getDescription() {
        return "Unknown frame type";
    }

    @Override
    public boolean isText() {
        return false;
    }

    @Override
    public boolean isPicture() {
        return false;
    }
}
