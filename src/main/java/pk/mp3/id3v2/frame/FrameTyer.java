package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.frame.frametype.FrameType;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public class FrameTyer extends CommonFrame implements Frame {

    public FrameTyer(FrameSource frameSource) {
        super(frameSource);
    }

    @Override
    public FrameType getType() {
        return null;
    }

    @Override
    public String getIdentifier() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public byte[] getPureData() {
        return new byte[0];
    }

    @Override
    public byte[] getSourceData() {
        return new byte[0];
    }
}
