package pk.mp3.id3v2.save;

import pk.mp3.id3v2.frame.Frame;
import pk.mp3.id3v2.tag.Id3v2Structure;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by pskhizhnyakov on 29.12.2015.
 */
public class FileBuilder {
    private File file;

    public FileBuilder(File file) {
        this.file = file;
    }

    public void build(Id3v2Structure structure) throws IOException {
        if (file == null) {
            throw new IOException("File is null");
        }
        if (!file.isFile()) {
            throw new IOException("File is not file");
        }
        if (!file.canWrite()) {
            throw new IOException("Can not write to file");
        }

        FileOutputStream fos = new FileOutputStream(file);

        //Save Id3v2 Header
        fos.write(structure.getHeaderSource().getIndicator());
        fos.write(structure.getHeaderSource().getVersion());
        fos.write(structure.getHeaderSource().getFlags());

        int id3v2Size = 0;
        for (Frame frame: structure.getFrames()) {
            id3v2Size += frame.getBytes() == null ? 0 : frame.getBytes().length;
        }
        fos.write(Id3v2Structure.getSize(id3v2Size + 10));

        for (Frame frame: structure.getFrames()) {
            fos.write(frame.getIdentifier().getBytes());
            fos.write(frame.getBytes().length + 10);
            fos.write(frame.getFrameSource().getFlags());
            fos.write(frame.getBytes());
        }

        fos.close();
    }

}
