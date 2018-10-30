package AudioEngine.MusicFiles;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Hugh on 10/29/2018.
 */
public class MusicFile {
    Clip clip;

    public MusicFile(AudioInputStream input) throws LineUnavailableException, IOException {
        clip = AudioSystem.getClip();
        clip.open(input);
    }

    public void start(){
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    
}
