package audio.player;

import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MusicStreamPlayer implements IMusicClipPlayer{
    private Clip clip;
    private AudioInputStream audioStream;
    private boolean isPlaying;

    private ArrayList<Notifiable> toNotify;

    public MusicStreamPlayer(AudioInputStream stream) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        this.audioStream = stream;
        clip = AudioSystem.getClip();
        clip.open(audioStream);
        System.out.println("length = " + clip.getMicrosecondLength() / 1000);
        clip.addLineListener(event -> {
            LineEvent.Type type = event.getType();
            if(LineEvent.Type.CLOSE.equals(type) || LineEvent.Type.STOP.equals(type)){
                isPlaying = false;
                clip.setFramePosition(0);

                System.out.println("Song ended");
                _notifyAll();


            }else if(LineEvent.Type.START.equals(type)){
                System.out.println("Started");
                System.out.println(event);
            } else {

                System.out.println("An event happened " + event + "." + event.getFramePosition());
            }
        });

//        clip.loop(Clip.LOOP_CONTINUOUSLY);
//        System.out.println(clip.getFrameLength());
//        clip.setLoopPoints(0, 721287);
//        clip.loop(3);
        setVolume(-10f);



    }

    private void _notifyAll(){
        if(toNotify != null) {
            for (Notifiable n: toNotify) {
                n.notifyObject(this);
            }

            toNotify = null;
        }
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
    public String toString() {
        return audioStream.toString();
    }

    @Override
    public boolean isPlaying(){
        return isPlaying;
    }

    @Override
    public void requestNotificationOnFinish(Notifiable obj) {
        if(toNotify == null){
            toNotify = new ArrayList<>();
        }

        toNotify.add(obj);
    }

    @Override
    public void play() {
        isPlaying = true;
//        clip.start();
        clip.loop(2);
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
