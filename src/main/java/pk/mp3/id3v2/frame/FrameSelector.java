package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.exception.IdentifierNotDeclaredException;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public interface FrameSelector {

    Frame selectByIdentifier(FrameSource frameSource) throws IdentifierNotDeclaredException;

}
