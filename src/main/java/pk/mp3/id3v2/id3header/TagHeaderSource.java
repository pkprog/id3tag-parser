package pk.mp3.id3v2.id3header;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public class TagHeaderSource {
    private byte[] indicator;
    private byte[] version;
    private byte[] flags;
    private byte[] size;

    public byte[] getIndicator() {
        return indicator;
    }

    public void setIndicator(byte[] indicator) {
        this.indicator = indicator;
    }

    public byte[] getVersion() {
        return version;
    }

    public void setVersion(byte[] version) {
        this.version = version;
    }

    public byte[] getFlags() {
        return flags;
    }

    public void setFlags(byte[] flags) {
        this.flags = flags;
    }

    public byte[] getSize() {
        return size;
    }

    public void setSize(byte[] size) {
        this.size = size;
    }
}
