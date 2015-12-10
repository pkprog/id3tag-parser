package pk.mp3.id3v2.frame;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public abstract class FrameFactory {
    public abstract Frame createApic(FrameSource frameSource);
    public abstract Frame createTyer(FrameSource frameSource);
    public abstract Frame createCommon(FrameSource frameSource);
    public abstract Frame createUnknown(FrameSource frameSource);
    public abstract Frame createTpe3(FrameSource frameSource);
}
