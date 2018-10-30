import AudioEngine.MusicFiles.MusicClipFactory;
import AudioEngine.MusicFiles.MusicClipPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Hugh on 10/29/2018.
 */
public class Main {
    private static Scanner reader;
    private static HashMap<String, MusicClipPlayer> clips = new HashMap<String, MusicClipPlayer>();

    private static String[] functionNames = {"load file"};

    public static void main(String[] args) {
        String response = "";

        reader = new Scanner(System.in);

        while(!"quit".equalsIgnoreCase(response)){

        }
    }

    private static void loadFile(){
        System.out.println("Enter the name of the file you wish to load: ");
        String fileName = reader.nextLine();

        try {

            File file = new File(fileName);
            MusicClipPlayer player = MusicClipFactory.makeMusicClipPlayer(fileName);

            clips.put(file.getName(), player);

            System.out.println("Successfully loaded file " + fileName + " as " + file.getName());

        } catch (LineUnavailableException e) {
            System.out.println("The line was unavailable for that file");
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An IO exception occured");
//            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("An unsupported audio file was loaded");
//            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Some other error happened");
            e.printStackTrace();
        }
    }
}
