package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.frame.frametype.FrameType;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public class FrameApic extends FrameCommon implements Frame, PictureFrame {

    public FrameApic(FrameType frameType, FrameSource frameSource) {
        super(frameType, frameSource);
    }

    @Override
    public byte[] getPicture() {
        return this.getSourceData();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(getIdentifier());
        if (this.getSourceData().length > 0) {
            stringBuilder.append(" Picture is present");
        } else {
            stringBuilder.append(" Picture is not present");
        }
        return stringBuilder.toString();
    }
}
