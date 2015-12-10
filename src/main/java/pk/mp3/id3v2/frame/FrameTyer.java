package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.frame.frametype.FrameTypeTyer;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public class FrameTyer extends CommonFrame implements Frame {

    public FrameTyer(FrameSource frameSource) {
        super(frameSource, new FrameTypeTyer());
    }

}
