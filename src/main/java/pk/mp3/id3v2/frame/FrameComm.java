package pk.mp3.id3v2.frame;

import pk.mp3.id3v2.frame.frametype.FrameType;
import pk.mp3.id3v2.parser.DifferentEncodingFlag;
import pk.mp3.id3v2.parser.TerminatingString;
import pk.mp3.id3v2.parser.TextDataParser;
import pk.mp3.id3v2.version.Version;
import pk.mp3.id3v2.version.VersionedUtilsFactory;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public class FrameComm implements CommentsFrame {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.ISO_8859_1;

    protected FrameSource frameSource;
    protected FrameType frameType;

    private String identifier;

    private String language;
    private Charset shortContentCharset;
    private StringBuilder shortContentDescription;
    private Charset actualTextCharset;
    private StringBuilder actualText;

    private TextDataParser textDataParser = VersionedUtilsFactory.getVersionUtils(Version.V230).getDataParser();

    public FrameComm(FrameType frameType, FrameSource frameSource) {
        this.frameSource = frameSource;
        this.frameType = frameType;
        this.identifier = new String(frameSource.getIdentifier(), DEFAULT_CHARSET);

        if (DifferentEncodingFlag.NONE.equals(textDataParser.getDifferentEncodingFlag(frameSource.getData()))) {

        } else {
            byte[] tempData = Arrays.copyOfRange(frameSource.getData(), 1, frameSource.getData().length);
            this.language = new String(Arrays.copyOfRange(tempData, 0, 3));

            tempData = Arrays.copyOfRange(frameSource.getData(), 4, frameSource.getData().length);
            TerminatingString terminatingString = textDataParser.getTerminatingString(tempData);

            this.shortContentDescription = new StringBuilder();
            this.actualText = new StringBuilder();

            if (TerminatingString.DEFAULT.equals(terminatingString)) {
                this.shortContentCharset = textDataParser.getCharset(tempData);
                int i;
                for (i = 0; i < tempData.length; i+=1) {
                    byte byte1 = tempData[i];
                    if (byte1 == 0x00) {
                        break;
                    }
                    String temp = new String(new byte[]{byte1}, this.shortContentCharset);
                    this.shortContentDescription.append(temp);
                }

                i+=1;

                this.actualTextCharset = textDataParser.getCharset(Arrays.copyOfRange(tempData, i, tempData.length));
                for (;i < tempData.length; i+=1) {
                    byte byte1 = tempData[i];
                    if (byte1 == 0x00) {
                        break;
                    }
                    String temp = new String(new byte[]{byte1}, this.actualTextCharset);
                    this.actualText.append(temp);
                }
            } else if (TerminatingString.UNICODE.equals(terminatingString)) {
                this.shortContentCharset = textDataParser.getCharset(tempData);
                int i;
                for (i = 0; i < tempData.length; i+=2) {
                    if (i < tempData.length && (i+1) < tempData.length) {
                        byte byte1 = tempData[i], byte2 = tempData[i + 1];
                        if (byte1 == 0x00 && byte2 == 0x00) {
                            break;
                        }
                        String temp = new String(textDataParser.getPureData(new byte[]{byte1, byte2}), this.shortContentCharset);
                        this.shortContentDescription.append(temp);
                    }
                }

                i+=2;

                this.actualTextCharset = textDataParser.getCharset(Arrays.copyOfRange(tempData, i, tempData.length));
                for (;i < tempData.length; i+=2) {
                    if (i < tempData.length && (i+1) < tempData.length) {
                        byte byte1 = tempData[i], byte2 = tempData[i + 1];
                        if (byte1 == 0x00 && byte2 == 0x00) {
                            break;
                        }
                        String temp = new String(textDataParser.getPureData(new byte[]{byte1, byte2}), this.actualTextCharset);
                        this.actualText.append(temp);
                    }
                }
            }

        }

    }

    @Override
    public FrameType getType() {
        return this.frameType;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

//    @Override
//    public int getSize() {
//        return getSizeInBytes();
//    }

    private int getSizeInBytes() {
        int iSize = (int)(frameSource.getSize()[0] << 24 & 0xffffffff) | (int)(frameSource.getSize()[1] << 16 & 0xffffff) | (int)(frameSource.getSize()[2] << 8 & 0xffff) | (int)(frameSource.getSize()[3] & 0xff);
        return iSize;
    }

    @Override
    public byte[] getSourceData() {
        return frameSource.getData();
    }

//    @Override
//    public Charset getCharset() throws UseOfMethodNotApplicable {
//        throw new UseOfMethodNotApplicable("This frame has many subframes");
//    }

//    public Charset getTextCharset() {
//        return StandardCharsets.UTF_16BE;
//    }

//    public StringBuilder getText() {
//        StringBuilder sbTemp = new StringBuilder();
//        sbTemp.append(dataParser.getPureData(Arrays.copyOfRange(frameSource.getData(), 4, frameSource.getData().length)));
//        return sbTemp;
//    }

    @Override
    public String getLanguage() {
//        return new String(Arrays.copyOfRange(frameSource.getData(), 1, 4));
        return language;
    }

    @Override
    public StringBuilder getShortContentDescrip() {
//        TerminatingString terminatingString = textDataParser.getTerminatingString(frameSource.getData());
//
//        StringBuilder result = new StringBuilder();
//
//        if (TerminatingString.DEFAULT.equals(terminatingString)) {
//            for (int i = 4; i < frameSource.getData().length; i+=1) {
//                byte byte1 = frameSource.getData()[i];
//                if (byte1 == 0x00) {
//                    break;
//                }
//                String temp = new String(new byte[]{byte1}, getActualTextCharset());
//                result.append(temp);
//            }
//        } else if (TerminatingString.UNICODE.equals(terminatingString)) {
//            for (int i = 4; i < frameSource.getData().length; i+=2) {
//                if (i < frameSource.getData().length && (i+1) < frameSource.getData().length) {
//                    byte byte1 = frameSource.getData()[i], byte2 = frameSource.getData()[i + 1];
//                    if (byte1 == 0x00 && byte2 == 0x00) {
//                        break;
//                    }
//                    String temp = new String(new byte[]{byte1, byte2}, getActualTextCharset());
//                    result.append(temp);
//                }
//            }
//        }
//
//        return result;
        return shortContentDescription;
    }

    @Override
    public StringBuilder getActualText() {
        return actualText;
    }

    @Override
    public Charset getShortContentDescripCharset() {
        return shortContentCharset;
    }

    @Override
    public Charset getActualTextCharset() {
        return actualTextCharset;
    }

    @Override
    public FrameSource getFrameSource() {
        return frameSource;
    }


}
