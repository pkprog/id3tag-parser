package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.frame.frametype.FrameTypeApic;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public class FrameApic extends CommonFrame implements Frame {

    public FrameApic(FrameSource frameSource) {
        super(frameSource, new FrameTypeApic());
    }

}
