package pk.mp3.id3v2.frame;

/**
 * Created by pskhizhnyakov on 23.12.2015.
 */
public class FrameUtils {

    public static String normId(String id) {
        if (id == null) return "";
        return id.trim().toUpperCase();
    }

    public static String toStringForTextFrame(TextFrame textFrame) {
        StringBuilder stringBuilder = new StringBuilder(textFrame.getIdentifier());
        stringBuilder.append(textFrame.getText());
        return stringBuilder.toString();
    }


}
