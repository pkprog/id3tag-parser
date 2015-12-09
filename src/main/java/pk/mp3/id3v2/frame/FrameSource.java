package pk.mp3.id3v2.frame;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public class FrameSource {
    private byte[] identifier;
    private byte[] size;
    private byte[] flags;
    private byte[] data;

    public byte[] getIdentifier() {
        return identifier;
    }

    public void setIdentifier(byte[] identifier) {
        this.identifier = identifier;
    }

    public byte[] getSize() {
        return size;
    }

    public void setSize(byte[] size) {
        this.size = size;
    }

    public byte[] getFlags() {
        return flags;
    }

    public void setFlags(byte[] flags) {
        this.flags = flags;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
