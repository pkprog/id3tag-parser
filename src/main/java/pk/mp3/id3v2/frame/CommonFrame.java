package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.frame.frametype.FrameType;
import pk.mp3.id3v2.frame.frametype.FrameTypeCommon;
import pk.mp3.id3v2.parser.DataParser;
import pk.mp3.id3v2.parser.DataParser230;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public class CommonFrame implements Frame {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.ISO_8859_1;

    protected FrameSource frameSource;
    protected FrameType frameType;

    private String identifier;

    private DataParser dataParser = new DataParser230();

    public CommonFrame(FrameSource frameSource) {
        this(frameSource, new FrameTypeCommon());
    }

    public CommonFrame(FrameSource frameSource, FrameType frameType) {
        this(frameSource, frameType, null);
    }

    public CommonFrame(FrameSource frameSource, FrameType frameType, String identifier) {
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

    @Override
    public int getSize() {
        return getSizeInBytes();
    }

    @Override
    public byte[] getPureData() {
        return dataParser.getPureData(frameSource.getData());
    }

    @Override
    public byte[] getSourceData() {
        return frameSource.getData();
    }

    private int getSizeInBytes() {
        int iSize = (int)(frameSource.getSize()[0] << 24 & 0xffffffff) | (int)(frameSource.getSize()[1] << 16 & 0xffffff) | (int)(frameSource.getSize()[2] << 8 & 0xffff) | (int)(frameSource.getSize()[3] & 0xff);
        return iSize;
    }

    @Override
    public Charset getCharset() {
        return dataParser.getCharset(frameSource.getData());
    }
}
