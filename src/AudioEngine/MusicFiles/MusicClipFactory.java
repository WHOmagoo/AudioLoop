package AudioEngine.MusicFiles;

import AudioEngine.Groupings.MusicClip;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Hugh on 10/29/2018.
 */
public class MusicClipFactory {
    public static MusicClipPlayer makeMusicClipPlayer(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        return makeMusicClipPlayer(new File(filename));
    }

    public static MusicClipPlayer makeMusicClipPlayer(URL url) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        return makeMusicClipPlayer(AudioSystem.getAudioInputStream(url));
    }

    public static MusicClipPlayer makeMusicClipPlayer(File file) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        return makeMusicClipPlayer(AudioSystem.getAudioInputStream(file));
    }

    public static MusicClipPlayer makeMusicClipPlayer(InputStream is) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        return makeMusicClipPlayer(AudioSystem.getAudioInputStream(is));
    }

    public static MusicClipPlayer makeMusicClipPlayer(AudioInputStream ais) throws IOException, LineUnavailableException {
        return new MusicClipPlayer(ais);
    }
}
