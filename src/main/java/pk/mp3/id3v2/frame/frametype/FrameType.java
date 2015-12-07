package pk.mp3.id3v2.frame.frametype;

/**
 * Created by pskhizhnyakov on 07.12.2015.
 */
public abstract class FrameType {
    private String name;
    private boolean isText;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isText() {
        return isText;
    }

    public void setIsText(boolean isText) {
        this.isText = isText;
    }
}
