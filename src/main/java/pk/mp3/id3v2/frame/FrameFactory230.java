package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.frame.frametype.FrameTypeTpe3;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public class FrameFactory230 extends FrameFactory {

    public FrameFactory230() {
    }

    @Override
    public Frame createApic(FrameSource frameSource) {
        return new FrameApic(frameSource);
    }

    @Override
    public Frame createTyer(FrameSource frameSource) {
        return new FrameTyer(frameSource);
    }

    @Override
    public Frame createCommon(FrameSource frameSource) {
        return new CommonFrame(frameSource);
    }

    @Override
    public Frame createUnknown(FrameSource frameSource) {
        return new UnknownFrame(frameSource);
    }

    @Override
    public Frame createTpe3(FrameSource frameSource) {
        return new CommonFrame(frameSource, new FrameTypeTpe3());
    }
}
