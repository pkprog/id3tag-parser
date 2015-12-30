package pk.mp3.id3v2;

import pk.mp3.id3v2.load.FileLoader;
import pk.mp3.id3v2.load.FileParser;
import pk.mp3.id3v2.load.SemanticLogger;
import pk.mp3.id3v2.save.FileBuilder;
import pk.mp3.id3v2.save.FileSaver;
import pk.mp3.id3v2.tag.Id3v2Structure;

import java.io.File;

/**
 * Created by pskhizhnyakov on 05.11.2015.
 */
public class Starter {

    private final static String TEST_FILE_NAME = "C:\\user_temp\\id3v2\\3run_media_-_3run_dlya_trenirovok_soydet__muzofon.org.mp3";
//    private final static String TEST_FILE_NAME = "C:\\user_temp\\id3v2\\imagine_dragons_-_radioactive_(zvukoff.ru).mp3";
//    private final static String TEST_FILE_NAME = "C:\\user_temp\\id3v2\\Grappler-Baki---2nd-season-all-alone(muzofon.com).mp3";
//    private final static String TEST_FILE_NAME = "C:\\user_temp\\id3v2\\01-Pesnya o druge.mp3";

    public static void main(String[] parameters) {
        System.out.println("*Start");
        FileLoader fileLoader = new FileLoader(TEST_FILE_NAME);

        try {
            File file = fileLoader.load();
            FileParser parser = new FileParser(file);
            Id3v2Structure parsed = parser.parse();
            SemanticLogger.log(parsed);

            FileSaver fileSaver = new FileSaver(TEST_FILE_NAME + ".new.mp3", file);
            File newFile = fileSaver.saveNew(parsed);
            FileBuilder fileBuilder = new FileBuilder(newFile);
            fileBuilder.build(parsed);
        } catch(Exception e) {
            System.out.println(e.getCause().getMessage());
        }
        System.out.println("*Finish");
    }

}
