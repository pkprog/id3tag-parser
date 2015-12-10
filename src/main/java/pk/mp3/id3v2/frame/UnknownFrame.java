package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.frame.frametype.FrameTypeUnknown;

/**
 * Created by pskhizhnyakov on 10.12.2015.
 */
public class UnknownFrame extends CommonFrame implements Frame {

    public UnknownFrame(FrameSource frameSource) {
        super(frameSource, new FrameTypeUnknown());
    }

    @Override
    public String getIdentifier() {
        return null;
    }

}
