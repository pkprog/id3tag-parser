package pk.mp3.id3v2.frame.frametype;

import pk.mp3.id3v2.frame.FrameCommon;
import pk.mp3.id3v2.frame.Frame;
import pk.mp3.id3v2.frame.FrameSource;

/**
 * Created by pskhizhnyakov on 07.12.2015.
 */
public class FrameTypeCommon implements FrameType {
    @Override
    public boolean isMyId(String id) {
        return false;
    }

    @Override
    public Frame createFrame(FrameSource frameSource) {
        return new FrameCommon(this, frameSource);
    }

    @Override
    public boolean isText() {
        return false;
    }

    @Override
    public boolean isPicture() {
        return false;
    }

    @Override
    public String getDescription() {
        return "All untyped frames";
    }

}
