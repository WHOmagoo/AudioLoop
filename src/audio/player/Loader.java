package audio.player;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface Loader {
    void reload() throws IOException, UnsupportedAudioFileException;
}
