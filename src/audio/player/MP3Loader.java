package audio.player;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;

public class MP3Loader implements Loader{
    String filePath;
    AudioInputStream inputStream;
    byte[] bytes;
    AudioFormat format;

    public MP3Loader(String filePath) throws IOException, UnsupportedAudioFileException {
        this.filePath = filePath;

        reload();
    }

    @Override
    public void reload() throws IOException, UnsupportedAudioFileException {
        inputStream = AudioSystem.getAudioInputStream(new FileInputStream(filePath));

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        this.format = inputStream.getFormat();
        bytes = inputStream.readAllBytes();

    }

    public AudioFormat getFormat(){
        return format;
    }

    public boolean matchesFormat(AudioFormat otherFormat){
        return format.matches(otherFormat);
    }
}
