package pk.mp3.id3v2.frame.frametype;

import pk.mp3.id3v2.frame.Frame;
import pk.mp3.id3v2.frame.FrameSource;
import pk.mp3.id3v2.frame.FrameTpe3;
import pk.mp3.id3v2.utils.FrameUtils;

/**
 * Created by pskhizhnyakov on 10.12.2015.
 */
public class FrameTypeTpe3 implements FrameType {
    private final String ID = "TPE3";

    @Override
    public boolean isMyId(String id) {
        return ID.equals(FrameUtils.normId(id));
    }

    @Override
    public Frame createFrame(FrameSource frameSource) {
        return new FrameTpe3(this, frameSource);
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
        return "Conductor/performer refinement";
    }
}
