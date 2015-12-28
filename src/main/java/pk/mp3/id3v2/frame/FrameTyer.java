package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.frame.frametype.FrameType;
import pk.mp3.id3v2.version.Version;
import pk.mp3.id3v2.version.VersionedUtilsFactory;

import java.nio.charset.Charset;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public class FrameTyer extends FrameCommon implements TextFrame {

    public FrameTyer(FrameType frameType, FrameSource frameSource) {
        super(frameType, frameSource);
    }

    @Override
    public StringBuilder getText() {
        return new StringBuilder(VersionedUtilsFactory.getVersionUtils(Version.V230).getDataParser().getCharacters(getSourceData()));
    }

    @Override
    public String toString() {
        return FrameUtils.toStringForTextFrame(this);
    }

    @Override
    public Charset getCharset() {
        return VersionedUtilsFactory.getVersionUtils(Version.V230).getDataParser().getCharset(getSourceData());
    }
}
