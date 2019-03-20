package audio.player;

import javax.sound.sampled.*;
import java.io.*;

public class MusicStreamPlayer implements IMusicClipPlayer{
    private Clip clip;
    private AudioInputStream audioStream;
    private boolean isPlaying;

    public MusicStreamPlayer(AudioInputStream stream) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        this.audioStream = stream;
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.addLineListener(event -> {
            LineEvent.Type type = event.getType();
            if(LineEvent.Type.CLOSE.equals(type) || LineEvent.Type.STOP.equals(type)){
                isPlaying = false;
                clip.setFramePosition(0);
                clip.start();
            }else if(LineEvent.Type.START.equals(type)){
                System.out.println("Started");
                System.out.println(event);
            } else {

                System.out.println("An event happened " + event + "." + event.getFramePosition());
            }
        });

//        clip.loop(Clip.LOOP_CONTINUOUSLY);
//        System.out.println(clip.getFrameLength());
        clip.setLoopPoints(0, 721287);
//        clip.loop(3);
        setVolume(-10f);



    }

    public void setVolume(float f){
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(f);
    }

    public void AddLineListener(LineListener listener){
        clip.addLineListener(listener);
    }

    public void removeLineListener(LineListener listener){
        clip.removeLineListener(listener);
    }

    @Override
    public boolean isPlaying(){
        return isPlaying;
    }

    @Override
    public void play() {
        isPlaying = true;
        clip.start();
    }

    @Override
    public void pause() {
        clip.stop();
    }

    @Override
    public void stop() {
        clip.stop();

        //Reset frame position;
        clip.setFramePosition(0);
    }

    @Override
    public long getLength() {
        return (long) Math.ceil(clip.getMicrosecondLength() / (1000));
    }

//    private void updateAudio(){
//        AudioFormat currentFormat = audioStream.getFormat();
//
//        AudioFormat.Encoding encoding = currentFormat.getEncoding();
//        float sampleRate = currentFormat.getSampleRate();
//        int sampleSizeInBits = currentFormat.getSampleSizeInBits();
//        int channels = currentFormat.getChannels();
//        int frameSize = currentFormat.getFrameSize();
//        float frameRate = currentFormat.getFrameRate();
//        boolean bigEndian = currentFormat.isBigEndian();
//
//        AudioFormat newFormat = new AudioFormat(encoding, sampleRate, sampleSizeInBits, channels, frameSize, frameRate, bigEndian);
//        System.out.println(currentFormat);
//        System.out.println(newFormat);
//
//        try {
//            audioStream.reset();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        AudioInputStream newAudioStream = AudioSystem.getAudioInputStream(newFormat, audioStream);
//        System.out.println(newAudioStream.getFormat());
//
////        AudioSystem.write(newAudioStream, newFormat, )
//        try {
//            clip = AudioSystem.getClip();
//            clip.open(newAudioStream);
//
//            clip.addLineListener(event -> {
//                LineEvent.Type type = event.getType();
//                if(LineEvent.Type.CLOSE.equals(type) || LineEvent.Type.STOP.equals(type)){
//                    isPlaying = false;
//                }
//            });
//
//        } catch (LineUnavailableException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void setSpeedModifier(double newSpeedModifier){
//        this.speedModifier = newSpeedModifier;
//        updateAudio();
//    }
}
