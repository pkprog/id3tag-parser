package pk.mp3.id3v2.tag;

import pk.mp3.id3v2.frame.Frame;
import pk.mp3.id3v2.tag.id3header.TagHeaderSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pskhizhnyakov on 05.11.2015.
 */
public class Id3v2Structure {
    private TagHeaderSource headerSource;
    private List<Frame> frames = new ArrayList<>();

    public TagHeaderSource getHeaderSource() {
        return headerSource;
    }

    public void setHeaderSource(TagHeaderSource headerSource) {
        this.headerSource = headerSource;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    /**
     * Size in bytes
     * @return
     */
    public int getSize() {
        int tempSize = (int)headerSource.getSize()[0] << 21 | (int)headerSource.getSize()[1] << 14 | (int)headerSource.getSize()[2] << 7 | (int)headerSource.getSize()[3];
        return tempSize;
    }

    public static byte[] getSize(int size) {
        byte[] result = new byte[] {
                (byte) (size >> 21),
                (byte) (size >> 14),
                (byte) (size >> 7),
                (byte) size
        };

        return result;
    }

}
