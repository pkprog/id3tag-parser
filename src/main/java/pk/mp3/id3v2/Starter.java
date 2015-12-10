package pk.mp3.id3v2;

import pk.mp3.id3v2.load.FileLoader;
import pk.mp3.id3v2.load.FileParser;
import pk.mp3.id3v2.load.SemanticLogger;

import java.io.File;

/**
 * Created by pskhizhnyakov on 05.11.2015.
 */
public class Starter {

    private final static String TEST_FILE_NAME = "C:\\user_temp\\id3v2\\3run_media_-_3run_dlya_trenirovok_soydet__muzofon.org.mp3";
//    private final static String TEST_FILE_NAME = "C:\\user_temp\\id3v2\\imagine_dragons_-_radioactive_(zvukoff.ru).mp3";
//    private final static String TEST_FILE_NAME = "C:\\user_temp\\id3v2\\Grappler-Baki---2nd-season-all-alone(muzofon.com).mp3";

    public static void main(String[] parameters) {
        FileLoader fileLoader = new FileLoader(TEST_FILE_NAME);

        try {
            File file = fileLoader.load();
            FileParser parser = new FileParser(file);
            SemanticLogger.log(parser.parse());

            System.out.println("Ok");
        } catch(Exception e) {
            System.out.println(e.getCause().getMessage());
        }
    }

}
