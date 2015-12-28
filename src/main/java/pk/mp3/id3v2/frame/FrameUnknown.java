package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.frame.frametype.FrameType;

/**
 * Created by pskhizhnyakov on 10.12.2015.
 */
public class FrameUnknown extends FrameCommon implements Frame {

    public FrameUnknown(FrameType frameType, FrameSource frameSource) {
        super(frameType, frameSource);
    }

    @Override
    public String getIdentifier() {
        return null;
    }

}
