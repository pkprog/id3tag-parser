package pk.mp3.id3v2.frame.frameselect;

import pk.mp3.id3v2.exception.IdentifierNotDeclaredException;
import pk.mp3.id3v2.frame.Frame;
import pk.mp3.id3v2.frame.FrameSource;
import pk.mp3.id3v2.parser.DataParser;
import pk.mp3.id3v2.parser.DataParser230;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public class FrameSelector230 implements FrameSelector {

    private FrameFactory frameFactory = new FrameFactory230();
    private DataParser parser = new DataParser230();

    public Frame selectByIdentifier(FrameSource frameSource) throws IdentifierNotDeclaredException {
        if (frameSource == null) return null;
        if (frameSource.getIdentifier() == null || frameSource.getIdentifier().length == 0)
            throw new IdentifierNotDeclaredException("Indetifier not declared");

        String id = new String(frameSource.getIdentifier(), parser.getCharset(frameSource.getData()));
        if (id == null || id.trim().length() == 0) {
            createUnknown(frameSource);
//            throw new IdentifierNotDeclaredException("Indetifier not declared");
        }

        return create(id, frameSource);
    }

    private Frame create(String id, FrameSource frameSource) {
        if (id.equals("APIC")) return frameFactory.createApic(frameSource);
        else if (id.equals("TYER")) return frameFactory.createTyer(frameSource);
        else if (id.equals("TPE3")) return frameFactory.createTpe3(frameSource);
        else return frameFactory.createCommon(frameSource);
    }

    private Frame createUnknown(FrameSource frameSource) {
        return frameFactory.createUnknown(frameSource);
    }

}
