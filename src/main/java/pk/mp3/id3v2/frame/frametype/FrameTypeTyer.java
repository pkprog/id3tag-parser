package pk.mp3.id3v2.frame.frametype;

import pk.mp3.id3v2.frame.Frame;
import pk.mp3.id3v2.frame.FrameSource;
import pk.mp3.id3v2.frame.FrameTyer;
import pk.mp3.id3v2.frame.FrameUtils;

/**
 * Created by pskhizhnyakov on 07.12.2015.
 */
public class FrameTypeTyer implements FrameType {
    private final String ID = "TYER";

    @Override
    public boolean isMyId(String id) {
        return ID.equals(FrameUtils.normId(id));
    }

    @Override
    public Frame createFrame(FrameSource frameSource) {
        return new FrameTyer(this, frameSource);
    }

    @Override
    public boolean isText() {
        return true;
    }

    @Override
    public boolean isPicture() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Year";
    }

}
