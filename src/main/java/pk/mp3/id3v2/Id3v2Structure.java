package pk.mp3.id3v2;

import pk.mp3.id3v2.frame.Frame;
import pk.mp3.id3v2.id3header.Id3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pskhizhnyakov on 05.11.2015.
 */
public class Id3v2Structure implements Id3 {
    private byte[] Id3Indicator;
    private byte[] version;
    private byte flagField;
    private byte[] size;

    private List<Frame> frames = new ArrayList<>();

    public byte[] getId3Indicator() {
        return Id3Indicator;
    }

    public void setId3Indicator(byte[] id3Indicator) {
        Id3Indicator = id3Indicator;
    }

    public byte[] getVersion() {
        return version;
    }

    public void setVersion(byte[] version) {
        this.version = version;
    }

    public byte getFlagField() {
        return flagField;
    }

    public void setFlagField(byte flagField) {
        this.flagField = flagField;
    }

    public byte[] getSize() {
        return size;
    }

    public void setSize(byte[] size) {
        this.size = size;
    }

    public int getSizeInBytes() {
        int iSize = (int)getSize()[0] << 21 | (int)getSize()[1] << 14 | (int)getSize()[2] << 7 | (int)getSize()[3];
        return iSize;
    }

    public List<Frame> getFrames() {
        return frames;
    }

}
