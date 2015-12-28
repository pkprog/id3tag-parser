package pk.mp3.id3v2.frame.frameselect;

import pk.mp3.id3v2.frame.frametype.FrameType;
import pk.mp3.id3v2.frame.frametype.FrameTypeApic;
import pk.mp3.id3v2.frame.frametype.FrameTypeComm;
import pk.mp3.id3v2.frame.frametype.FrameTypeTpe3;
import pk.mp3.id3v2.frame.frametype.FrameTypeTyer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pskhizhnyakov on 22.12.2015.
 */
public class AllFrameTypesList {
    private List<FrameType> frameTypes;

    public AllFrameTypesList() {
    }

    private void initList() {
        frameTypes = new ArrayList<>();
        frameTypes.add(new FrameTypeApic());
        frameTypes.add(new FrameTypeComm());
        frameTypes.add(new FrameTypeTpe3());
        frameTypes.add(new FrameTypeTyer());
    }

    public List<FrameType> getFrames() {
        List<FrameType> frameTypesLocal = frameTypes;
        if (frameTypesLocal == null) {
            synchronized (AllFrameTypesList.class) {
                frameTypesLocal = frameTypes;
                if (frameTypesLocal == null) {
                    initList();
                    frameTypesLocal = frameTypes;
                }
            }
        }
        return frameTypesLocal;
    }

}
