package pk.mp3.id3v2.frame.frametype;

/**
 * Created by pskhizhnyakov on 11.12.2015.
 */
public class FrameTypeComm implements FrameType {

    @Override
    public String getName() {
        return "Comments";
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
