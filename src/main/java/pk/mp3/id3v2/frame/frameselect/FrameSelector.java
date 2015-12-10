package pk.mp3.id3v2.frame.frameselect;

import pk.mp3.id3v2.exception.IdentifierNotDeclaredException;
import pk.mp3.id3v2.frame.Frame;
import pk.mp3.id3v2.frame.FrameSource;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public interface FrameSelector {

    Frame selectByIdentifier(FrameSource frameSource) throws IdentifierNotDeclaredException;

}
