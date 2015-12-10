package pk.mp3.id3v2.frame.frametype;

/**
 * Created by pskhizhnyakov on 10.12.2015.
 */
public class FrameTypeUnknown implements FrameType {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isText() {
        return false;
    }

    @Override
    public boolean isPicture() {
        return false;
    }
}
