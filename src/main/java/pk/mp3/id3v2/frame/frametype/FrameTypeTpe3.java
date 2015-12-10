package pk.mp3.id3v2.frame.frametype;

/**
 * Created by pskhizhnyakov on 10.12.2015.
 */
public class FrameTypeTpe3 implements FrameType {
    @Override
    public String getName() {
        return "Conductor/performer refinement";
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
