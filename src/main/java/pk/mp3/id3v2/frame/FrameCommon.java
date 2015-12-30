package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.frame.frametype.FrameType;
import pk.mp3.id3v2.parser.TextDataParser;
import pk.mp3.id3v2.parser.TextDataParser230;
import pk.mp3.id3v2.utils.FrameUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public class FrameCommon implements Frame {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.ISO_8859_1;

    protected FrameSource frameSource;
    protected FrameType frameType;

    private String identifier;

    private TextDataParser textDataParser = new TextDataParser230();

    public FrameCommon(FrameType frameType, FrameSource frameSource) {
        this(frameType, frameSource, null);
    }

    public FrameCommon(FrameType frameType, FrameSource frameSource, String identifier) {
        if (frameSource == null)
            throw new NullPointerException("FrameSource can not be NULL");
        if (frameType == null)
            throw new NullPointerException("FrameType can not be NULL");

        if (identifier != null && identifier.trim().length() > 0) {
            this.identifier = identifier;
        } else {
            this.identifier = new String(frameSource.getIdentifier(), DEFAULT_CHARSET);
        }
        this.frameType = frameType;
        this.frameSource = frameSource;
    }

    @Override
    public FrameType getType() {
        return frameType;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

//    @Override
//    public int getSize() {
//        return getSizeInBytes();
//    }

    @Override
    public byte[] getSourceData() {
        return frameSource.getData();
    }

    private int getSizeInBytes() {
        return FrameUtils.getFrameSize(frameSource.getSize());
//        int iSize = (int)(frameSource.getSize()[0] << 24 & 0xffffffff) | (int)(frameSource.getSize()[1] << 16 & 0xffffff) | (int)(frameSource.getSize()[2] << 8 & 0xffff) | (int)(frameSource.getSize()[3] & 0xff);
//        return iSize;
    }

//    @Override
//    public Charset getCharset() {
//        return dataParser.getCharset(frameSource.getData());
//    }


    @Override
    public FrameSource getFrameSource() {
        return frameSource;
    }

    @Override
    public byte[] getBytes() {
        return frameSource.getData();
    }
}
