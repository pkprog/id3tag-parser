package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.exception.UseOfMethodNotApplicable;
import pk.mp3.id3v2.frame.frametype.FrameType;
import pk.mp3.id3v2.frame.frametype.FrameTypeComm;
import pk.mp3.id3v2.parser.DataParser;
import pk.mp3.id3v2.parser.DataParser230;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public class FrameComm implements Frame {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.ISO_8859_1;

    protected FrameSource frameSource;
    protected FrameType frameType;

    private String identifier;

    private DataParser dataParser = new DataParser230();

    public FrameComm(FrameSource frameSource) {
        this.frameSource = frameSource;
        this.frameType = new FrameTypeComm();
        this.identifier = new String(frameSource.getIdentifier(), DEFAULT_CHARSET);
    }

    @Override
    public FrameType getType() {
        return this.frameType;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public int getSize() {
        return getSizeInBytes();
    }

    private int getSizeInBytes() {
        int iSize = (int)(frameSource.getSize()[0] << 24 & 0xffffffff) | (int)(frameSource.getSize()[1] << 16 & 0xffffff) | (int)(frameSource.getSize()[2] << 8 & 0xffff) | (int)(frameSource.getSize()[3] & 0xff);
        return iSize;
    }

    @Override
    public byte[] getPureData() throws UseOfMethodNotApplicable {
        throw new UseOfMethodNotApplicable("This frame has many subframes");
    }

    @Override
    public byte[] getSourceData() {
        return frameSource.getData();
    }

    @Override
    public Charset getCharset() throws UseOfMethodNotApplicable {
        throw new UseOfMethodNotApplicable("This frame has many subframes");
    }

    public Charset getTextCharset() {
        return StandardCharsets.UTF_16BE;
    }

    public byte[] getLanguage() {
        return Arrays.copyOfRange(frameSource.getData(), 1, 4);
    }

    public byte[] getShortDescription() {
        return null;
    }

    public byte[] getText() {
        return  dataParser.getPureData(Arrays.copyOfRange(frameSource.getData(), 4, frameSource.getData().length));
    }


}
