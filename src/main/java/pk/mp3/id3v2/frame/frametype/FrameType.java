package pk.mp3.id3v2.frame.frametype;

/**
 * Created by pskhizhnyakov on 07.12.2015.
 */
public interface FrameType {
    String getName();
    boolean isText();
    boolean isPicture();
}
