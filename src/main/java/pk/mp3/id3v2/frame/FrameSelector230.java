package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.exception.IdentifierNotDeclaredException;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public class FrameSelector230 implements FrameSelector {

    private FrameFactory frameFactory = new FrameFactory230();

    public Frame selectByIdentifier(FrameSource frameSource) throws IdentifierNotDeclaredException {
        if (frameSource == null) return null;
        if (frameSource.getIdentifier() == null || frameSource.getIdentifier().length == 0)
            throw new IdentifierNotDeclaredException("Indetifier not declared");

        DataParser parser = new DataParser230(frameSource.getData());

        String id = new String(frameSource.getIdentifier(), parser.getCharset());
        if (id == null || id.trim().length() == 0)
            throw new IdentifierNotDeclaredException("Indetifier not declared");

        return create(id, frameSource);
    }

    private Frame create(String id, FrameSource frameSource) {
        if (id.equals("APIC")) return frameFactory.createApic(frameSource);
        else if (id.equals("TYER")) return frameFactory.createTyer(frameSource);
        else return frameFactory.createCommon(frameSource);
    }

}
