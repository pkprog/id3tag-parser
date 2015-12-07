package pk.mp3.id3v2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by pskhizhnyakov on 05.11.2015.
 * Id3v2.3.0
 */
public class FileParser {
    private final int TAG_HEADER_SIZE_IN_BYTES = 10;
    private final int FRAME_HEADER_SIZE_IN_BYTES = 10;

    private File file;

    public FileParser(File file) throws FileNotFoundException {
        if (file == null) {
            throw new FileNotFoundException("File is null");
        }
        this.file = file;
    }

    public Id3v2Structure parse() throws FileNotFoundException, IOException {
        Id3v2Structure result = new Id3v2Structure();
        FileInputStream fileInputStream = new FileInputStream(file);

        //Header 10 bytes
        result.setId3Indicator(new byte[3]);
        fileInputStream.read(result.getId3Indicator(), 0, 3);

        result.setVersion(new byte[2]);
        fileInputStream.read(result.getVersion(), 0, 2);

        byte[] temp = new byte[1];
        fileInputStream.read(temp, 0, 1);
        result.setFlagField(temp[0]);

        byte[] temp2 = new byte[4];
        fileInputStream.read(temp2, 0, 4);
        result.setSize(temp2);

        //Frames
        int totalReadBytes = TAG_HEADER_SIZE_IN_BYTES;
        while (totalReadBytes < result.getSizeInBytes()) {
            byte[] tempFrameHeader = new byte[FRAME_HEADER_SIZE_IN_BYTES];
            int realReadBytesFrameHeader = fileInputStream.read(tempFrameHeader, 0, FRAME_HEADER_SIZE_IN_BYTES);
            Id3v2Structure.Frame frame = result.new Frame(Arrays.copyOf(tempFrameHeader, 4));
            frame.setSize(Arrays.copyOfRange(tempFrameHeader, 4, 8));
            frame.setFlags(Arrays.copyOfRange(tempFrameHeader, 8, 10));

            if (frame.getSizeInBytes() > 0) {
                result.getFrames().add(frame);

                byte[] tempFrameData = new byte[frame.getSizeInBytes()];
                int realReadBytesFrameData = fileInputStream.read(tempFrameData, 0, tempFrameData.length);
                frame.setData(tempFrameData);

                totalReadBytes += realReadBytesFrameData;
            }

            totalReadBytes += realReadBytesFrameHeader;
        }

        if (TAG_HEADER_SIZE_IN_BYTES >= result.getSizeInBytes()) {
            System.out.println("No frames found");
        }

        System.out.println("Read " + totalReadBytes + " bytes");

        fileInputStream.close();
        return result;
    }

}
