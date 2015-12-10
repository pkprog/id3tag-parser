package pk.mp3.id3v2.frame.frametype;

/**
 * Created by pskhizhnyakov on 07.12.2015.
 */
public class FrameTypeApic implements FrameType {

    @Override
    public String getName() {
        return "Attached picture";
    }

    @Override
    public boolean isText() {
        return false;
    }

    @Override
    public boolean isPicture() {
        return true;
    }
}
