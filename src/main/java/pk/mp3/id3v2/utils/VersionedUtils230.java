package pk.mp3.id3v2.utils;

import pk.mp3.id3v2.parser.TextDataParser;
import pk.mp3.id3v2.parser.TextDataParser230;

/**
 * Created by pskhizhnyakov on 25.12.2015.
 */
public class VersionedUtils230 implements VersionedUtils {
    private static volatile TextDataParser230 textDataParser230;

    //Double-chacked singleton
    @Override
    public TextDataParser getDataParser() {
        TextDataParser230 temp = textDataParser230;
        if (temp == null) {
            synchronized (VersionedUtils230.class) {
                temp = textDataParser230;
                if (temp == null) {
                    textDataParser230 = temp = new TextDataParser230();
                }
            }
        }
        return temp;
    }
}
