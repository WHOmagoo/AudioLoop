package audio.engine;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class ClipPlayerMain {

    public static void main(String[] args) {

        class AudioListener implements LineListener {

            private boolean done = false;

            @Override
            public synchronized void update(LineEvent event) {
                LineEvent.Type eventType = event.getType();
                if (eventType == LineEvent.Type.STOP || eventType == LineEvent.Type.CLOSE) {
                    done = true;
                    notifyAll();
                }
            }
            private synchronized void waitUntilDone() throws InterruptedException {
                while (!done) { wait(); }
            }
        }

        try {
            File yourFile = new File("Classic Electric Piano_1.wav");
            File fileTwo = new File("JAZZ ORGAN_1.wav");
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            AudioListener listener = new AudioListener();
            clip.addLineListener(listener);
            clip.open(stream);
            try {
                clip.start();
                listener.waitUntilDone();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                clip.close();
            }

        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

//        AudioInputStream track = new AudioInputStream();
//        MusicClipPlayer player = new MusicClipPlayer();
    }

}
