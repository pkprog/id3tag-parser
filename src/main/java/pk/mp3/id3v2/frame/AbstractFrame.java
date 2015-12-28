package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.frame.frametype.FrameType;

/**
 * Created by pskhizhnyakov on 23.12.2015.
 */
public abstract class AbstractFrame {
    protected FrameSource frameSource;
    protected FrameType frameType;

    public AbstractFrame(FrameType frameType, FrameSource frameSource) {
        this.frameSource = frameSource;
        this.frameType = frameType;
    }

}
