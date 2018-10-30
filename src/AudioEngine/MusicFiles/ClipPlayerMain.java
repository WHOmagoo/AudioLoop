package AudioEngine.MusicFiles;

import javax.sound.sampled.*;
import javax.sound.sampled.spi.AudioFileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ClipPlayerMain {

    public static void main(String[] args) {

        try {
            File yourFile = new File("70s Funk Clav_1.wav");
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
            while (!clip.isRunning())
                Thread.sleep(10);
            while (clip.isRunning())
                Thread.sleep(10);
            clip.close();
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException | InterruptedException e) {
            e.printStackTrace();
        }

//        AudioInputStream track = new AudioInputStream();
//        MusicClipPlayer player = new MusicClipPlayer();
    }

}
