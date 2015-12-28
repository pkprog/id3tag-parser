package pk.mp3.id3v2.frame.frameselect;

import pk.mp3.id3v2.exception.IdentifierNotDeclaredException;
import pk.mp3.id3v2.frame.Frame;
import pk.mp3.id3v2.frame.FrameSource;
import pk.mp3.id3v2.frame.FrameUtils;
import pk.mp3.id3v2.frame.frametype.FrameType;
import pk.mp3.id3v2.frame.frametype.FrameTypeCommon;
import pk.mp3.id3v2.frame.frametype.FrameTypeUnknown;
import pk.mp3.id3v2.parser.TextDataParser;
import pk.mp3.id3v2.parser.TextDataParser230;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public class FrameSelector230 implements FrameSelector {
    private FrameTypeUnknown frameTypeUnknown = new FrameTypeUnknown();
    private FrameTypeCommon frameTypeCommon = new FrameTypeCommon();

    private TextDataParser parser = new TextDataParser230();
    private AllFrameTypesList allFrameTypesList = new AllFrameTypesList();

    public Frame selectByIdentifier(FrameSource frameSource) throws IdentifierNotDeclaredException {
        if (frameSource == null) return null;
        if (frameSource.getIdentifier() == null || frameSource.getIdentifier().length == 0)
            throw new IdentifierNotDeclaredException("Indetifier not declared");

        String id = new String(frameSource.getIdentifier(), parser.getCharset(frameSource.getData()));
        if (FrameUtils.normId(id).equals("")) {
            return createUnknown(frameSource);
//            throw new IdentifierNotDeclaredException("Indetifier not declared");
        } else {
            return create(id, frameSource);
        }
    }

    private Frame create(String id, FrameSource frameSource) {
        for (FrameType frameType: allFrameTypesList.getFrames()) {
            if (frameType.isMyId(id)) {
                return frameType.createFrame(frameSource);
            }
        }

        return createCommon(frameSource);
    }

    private Frame createUnknown(FrameSource frameSource) {
        return frameTypeUnknown.createFrame(frameSource);
    }

    private Frame createCommon(FrameSource frameSource) {
        return frameTypeCommon.createFrame(frameSource);
    }

}
