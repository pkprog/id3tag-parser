package pk.mp3.id3v2.version;

import pk.mp3.id3v2.exception.VersionNotFoundException;
import pk.mp3.id3v2.utils.VersionedUtils;
import pk.mp3.id3v2.utils.VersionedUtils230;

/**
 * Created by pskhizhnyakov on 25.12.2015.
 */
public class VersionedUtilsFactory {
    private static VersionedUtils230 utils230 = new VersionedUtils230();

    public static VersionedUtils getVersionUtils(Version version) throws VersionNotFoundException{
        if (Version.V230.equals(version)) {
            return utils230;
        }

        throw new VersionNotFoundException("Version not found: " + String.valueOf(version));
    }

}
