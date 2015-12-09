package pk.mp3.id3v2.frame;

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
}
