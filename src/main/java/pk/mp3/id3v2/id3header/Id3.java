package pk.mp3.id3v2.id3header;

/**
 * Created by pskhizhnyakov on 09.12.2015.
 */
public interface Id3 {
    byte[] getId3Indicator();
    byte[] getVersion();
    byte getFlagField();
    byte[] getSize();
    int getSizeInBytes();
}
