package pk.mp3.id3v2.frame.frametype;

import pk.mp3.id3v2.frame.Frame;
import pk.mp3.id3v2.frame.FrameComm;
import pk.mp3.id3v2.frame.FrameSource;
import pk.mp3.id3v2.frame.FrameUtils;

/**
 * Created by pskhizhnyakov on 11.12.2015.
 */
public class FrameTypeComm implements FrameType {
    private final String ID = "COMM";

    @Override
    public boolean isMyId(String id) {
        return ID.equals(FrameUtils.normId(id));
    }

    @Override
    public Frame createFrame(FrameSource frameSource) {
        return new FrameComm(this, frameSource);
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
        return "Comments";
    }

}
