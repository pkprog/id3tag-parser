package pk.mp3.id3v2.save;

import pk.mp3.id3v2.tag.Id3v2Structure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by pskhizhnyakov on 29.12.2015.
 */
public class FileSaver {
    private String fileName;
    private File newFile;
    private File oldFile;

    public FileSaver(String fileName, File oldFile) {
        this.fileName = fileName;
        this.oldFile = oldFile;
    }

    public File saveNew(Id3v2Structure structure) throws IOException {
        return save(structure, true);
    }

    public File save(Id3v2Structure structure, boolean saveToNewFile) throws IOException {
        newFile = new File(fileName);

        if (saveToNewFile) {
            if (newFile.exists()) {
                newFile.delete();
            }
            if (!newFile.createNewFile()) {
                throw new IOException("File not created:" + fileName);
            }
        } else {
            if (!newFile.exists() || !newFile.isFile()) {
                throw new FileNotFoundException("File not found:" + fileName);
            }
        }

        return newFile;
    }

}
