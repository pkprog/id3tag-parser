package pk.mp3.id3v2;

import pk.mp3.id3v2.frame.Frame;
import pk.mp3.id3v2.frame.frametype.FrameTypeTpe3;
import pk.mp3.id3v2.frame.frametype.FrameTypeUnknown;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by pskhizhnyakov on 05.11.2015.
 */
public class SemanticLogger {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.ISO_8859_1;

    public static void log(Id3v2Structure structure) {
        if (structure == null) return;

        //Id3 indicator
        System.out.println(new String(structure.getHeaderSource().getIndicator()));

        //Version
        System.out.println("" + structure.getHeaderSource().getVersion()[0] + "." + structure.getHeaderSource().getVersion()[1]);

        //Flags
        if (((byte) 0b10000000 & structure.getHeaderSource().getFlags()[0]) != 0) {
            System.out.println("Flag:" + "a" + ", unsynchronisation is used");
        }
        if (((byte) 0b01000000 & structure.getHeaderSource().getFlags()[0]) != 0) {
            System.out.println("Flag:" + "b" + ", compression is used");
        }
        if (((byte) 0b00100000 & structure.getHeaderSource().getFlags()[0]) != 0) {
            System.out.println("Flag:" + "c");
        }

        for (int i = 0; i < structure.getFrames().size(); i++) {
            Frame frame = structure.getFrames().get(i);
            if (frame.getType() instanceof FrameTypeUnknown) {
            } else {
                System.out.println(i + "." + new String(frame.getIdentifier()) + ", frame data size: " + frame.getSize());
                if (frame.getSize() > 0) {
                    if (frame.getType().isPicture()) {
                        System.out.println("Frame data: " + "Picture found");
                    } else {
                        System.out.println("Frame data: " + new String(frame.getPureData(), frame.getCharset()));
                    }
                }
            }

            if (frame.getIdentifier().equals("COMM")) {
                Charset ch = frame.getCharset();
                System.out.println("*COMM found");
            }

            if (frame.getType() instanceof FrameTypeTpe3) {
                Charset ch = frame.getCharset();
                String s = new String(frame.getPureData(), frame.getCharset());
                System.out.println("*TPE3 found");
            }
        }

        //Size
        //https://social.msdn.microsoft.com/Forums/vstudio/en-US/ce8672f9-c4dd-40bb-baff-5d62027314bc/understanding-the-size-part-of-id3v23-tag-header-mp3?forum=csharpgeneral

/*
        header.size=(((quint32)rawHeader[6]<<21)&0b00001111111000000000000000000000)+
                (((quint32)rawHeader[7]<<14)&0b00000000000111111100000000000000)+
                (((quint32)rawHeader[8]<<7) &0b00000000000000000011111110000000)+
                ( (quint32)rawHeader[9]     &0b00000000000000000000000001111111);
*/

//        System.out.println("Size:" + structure.getSize());
//        int iSize = (int)structure.getSize()[0] << 21 | (int)structure.getSize()[1] << 14 | (int)structure.getSize()[2] << 7 | (int)structure.getSize()[3];
        System.out.println("Size:" + structure.getSize());
    }

}
