package demo;

import audio.editor.AudioInputStreamEditor;
import audio.player.ManyClipsPlayer;
import audio.player.MusicStreamPlayer;

import javax.sound.sampled.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MusicPlayerDemo {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
//        Mixer.Info[] info = AudioSystem.getMixerInfo();
//
//        for (Mixer.Info inf : info){
//
//            Mixer m = AudioSystem.getMixer(inf);
//
//            boolean speakerSupported = m.isLineSupported(Port.Info.SPEAKER);
//            boolean other = m.isLineSupported(Port.Info.HEADPHONE);
//
//            if(speakerSupported || other) {
//
//                System.out.println(inf.getName());
//                System.out.println("\t" + inf.getDescription());
//                System.out.println("\tSpeaker is supported: " + speakerSupported);
//                System.out.println("\tSupports synchronization " + m.isSynchronizationSupported(m.getSourceLines(), false));
//                System.out.println();
//
//                try {
//                    Clip c = AudioSystem.getClip(inf);
//                } catch (Exception e){
//                    e.printStackTrace();
//                }
//
//                Line[] lines = m.getSourceLines();
//
//                for (Line line: lines) {
//                    System.out.println("\t\t");
//                }
//            }
//
//        }


        File f = new File("Jazz ORGAN_1.wav");

        byte fileBuf[] = Files.readAllBytes(Paths.get("Jazz ORGAN_1.wav"));

        ByteArrayOutputStream fileBuffer = new ByteArrayOutputStream(fileBuf.length);

        AudioInputStream stream = AudioSystem.getAudioInputStream(new File("Jazz ORGAN_1.wav"));
        AudioInputStream stream11 = AudioSystem.getAudioInputStream(new File("70s Funk Clav_1.wav"));
        AudioInputStream stream1 = AudioSystem.getAudioInputStream(new File("SoCal_1.wav"));
        AudioInputStream stream2 = AudioSystem.getAudioInputStream(new File("Classic Electric Piano_1.wav"));

//        MusicStreamPlayer music = new MusicStreamPlayer(stream);

//        AudioInputStreamEditor editor = new AudioInputStreamEditor(stream, 4);
        MusicStreamPlayer jazzOrgan = new MusicStreamPlayer(stream);
        MusicStreamPlayer funk70s = new MusicStreamPlayer(stream11);
        MusicStreamPlayer soCal = new MusicStreamPlayer(stream1);
        MusicStreamPlayer electricPiano = new MusicStreamPlayer(stream2);

//        Thread.sleep(100);

        ManyClipsPlayer player = new ManyClipsPlayer();

        player.add(jazzOrgan);
        player.add(funk70s);
        player.add(soCal);
        player.add(electricPiano);

        player.play();

        System.in.read();
    }
}
