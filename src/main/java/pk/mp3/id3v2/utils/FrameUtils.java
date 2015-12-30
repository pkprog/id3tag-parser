package pk.mp3.id3v2.utils;

import pk.mp3.id3v2.frame.TextFrame;

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

    public static int getFrameSize(byte[] frameSizeBytes) {
        int iSize = (int)(frameSizeBytes[0] << 24 & 0xffffffff) | (int)(frameSizeBytes[1] << 16 & 0xffffff) | (int)(frameSizeBytes[2] << 8 & 0xffff) | (int)(frameSizeBytes[3] & 0xff);
        return iSize;
    }

}
