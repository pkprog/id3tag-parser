package pk.mp3.id3v2.load;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by pskhizhnyakov on 05.11.2015.
 */
public class FileLoader {
    private String fileName;
    private File file;

    public FileLoader(String fileName) {
        this.fileName = fileName;
    }

    public File load() throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            throw new FileNotFoundException("File not found:" + fileName);
        }

        return file;
    }

}
