package pk.mp3.id3v2.frame.frametype;

/**
 * Created by pskhizhnyakov on 07.12.2015.
 */
public class FrameTypeTyer implements FrameType {

    @Override
    public String getName() {
        return "Year";
    }

    @Override
    public boolean isText() {
        return true;
    }

    @Override
    public boolean isPicture() {
        return false;
    }
}
