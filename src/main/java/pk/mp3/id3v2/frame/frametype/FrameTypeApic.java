package pk.mp3.id3v2.frame.frametype;

import pk.mp3.id3v2.frame.Frame;
import pk.mp3.id3v2.frame.FrameApic;
import pk.mp3.id3v2.frame.FrameSource;
import pk.mp3.id3v2.utils.FrameUtils;

/**
 * Created by pskhizhnyakov on 07.12.2015.
 * Это по сути шаблонов проектирования "Фабрика, порождающая FrameApic"
 */
public class FrameTypeApic implements FrameType {
    private final String ID = "APIC";

    @Override
    public boolean isMyId(String id) {
        return ID.equals(FrameUtils.normId(id));
    }

    @Override
    public Frame createFrame(FrameSource frameSource) {
        return new FrameApic(this, frameSource);
    }

    @Override
    public boolean isText() {
        return false;
    }

    @Override
    public boolean isPicture() {
        return true;
    }

    @Override
    public String getDescription () {
        return "Attached picture";
    }
}
