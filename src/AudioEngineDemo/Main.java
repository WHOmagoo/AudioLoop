package AudioEngineDemo;

import AudioEngine.Groupings.MusicClip;
import AudioEngine.Groupings.MusicGrouping;
import AudioEngine.MusicFiles.MusicClipFactory;
import AudioEngine.MusicFiles.MusicClipPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.security.KeyException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Hugh on 10/29/2018.
 */
public class Main {
    private static Scanner reader;
    private static HashMap<String, MusicClipPlayer> musicPlayer = new HashMap<String, MusicClipPlayer>();
    private static HashMap<String, MusicClip> musicClip = new HashMap<String, MusicClip>();
    private static HashMap<String, MusicGrouping> musicGroups = new HashMap<String, MusicGrouping>();

    private static Map<String, Runnable> commands = new HashMap<>();

    public static void main(String[] args) {
        String response = "";

        initializeFunctionMap();

        loadClips();
        listClipNames();

        reader = new Scanner(System.in);

        while(!"quit".equalsIgnoreCase(response)){
            prompt();
            response = reader.nextLine();
            if(commands.containsKey(response)){
                commands.get(response).run();
            } else {
                System.out.println("Unkown input, please try again");
            }
        }
    }

    private static void listClipNames(){
        System.out.print("Clip name: ");
        for(String s : musicClip.keySet()){
            System.out.print(s + ", ");
        }

        System.out.println(".");
    }

    private static void loadClips(){
        String pathname;
        String name;
        MusicClipPlayer player;

        pathname = "70s Funk Clav_1.wav";
        name = "Clav";
        loadClipPlayer(name, pathname);
        loadClip(name);

        pathname = "SoCal_1.wav";
        name = "drum";
        loadClipPlayer(name, pathname);
        loadClip(name);

        pathname = "Classic Electric Piano_1.wav";
        name = "piano";
        loadClipPlayer(name, pathname);
        loadClip(name);

        pathname = "JAZZ ORGAN_1.wav";
        name = "organ";
        loadClipPlayer(name, pathname);
        loadClip(name);
    }

    private static void loadClip(String name){
        musicClip.put(name, new MusicClip());
    }

    private static void loadClipPlayer(String name, String pathname){
        try {
            MusicClipPlayer player = MusicClipFactory.makeMusicClipPlayer(pathname);
            musicPlayer.put(name, player);
        }  catch (LineUnavailableException e) {
            System.out.println("The line was unavailable for that file");
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An IO exception occurred for " + pathname);
//            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            System.out.println("An unsupported audio file was loaded for " + pathname);
//            e.printStackTrace();
        } catch (Exception e){
            System.out.println("Some other error happened for " + pathname);
            e.printStackTrace();
        }
    }

    private static void initializeFunctionMap(){
        commands.put("load file", Main::loadFile);
        commands.put("quit", Main::quit);
        commands.put("group", Main::group);
        commands.put("list", Main::list);
    }

    private static void list() {
        System.out.println("Music Clips loaded\n");

        for(String s : musicClip.keySet()){
            System.out.println(s);
        }

        System.out.println();

        System.out.println("Groups Loaded");

        for(String group : musicGroups.keySet()){
            System.out.println(group);
            for(String subItem : musicClip.keySet()){
                if(musicGroups.get(group).contains(musicClip.get(subItem))){
                    System.out.println("\t" + subItem);
                }
            }
        }
    }

    private static void group() {
        System.out.print("Please enter a name for this group: ");
        String response = reader.nextLine();

        while(musicGroups.containsKey(response)){
            System.out.println("Please enter another name for the group, that name is already used: ");
        }

        MusicGrouping newGroup = new MusicGrouping();

        musicGroups.put(response, newGroup);

        System.out.println("Please enter an item you would like to group,  empty means finished:");

        response = reader.nextLine();

        while(!response.equalsIgnoreCase("")){
            System.out.print("Name of clip to add: ");

            newGroup.add(musicClip.get(response));


            response = reader.nextLine();
        }

        System.out.println("Successfully created new group");

    }

    private static void prompt(){
        System.out.print("Please enter a command (");

        for(String command : commands.keySet()){
            System.out.print(command + ", ");
        }

        System.out.print(") : ");
    }

    private static void quit(){
        System.out.println("Cya!");
    }

    private static String chopFileExtenstion(String filePath){
        Pattern p = Pattern.compile("(\\w*)\\.\\w*$");

        Matcher m = p.matcher(filePath);

        try {
            return m.group(0);
        } catch(IllegalStateException e){
            System.out.println(filePath + " did not match a file name");
            return filePath;
        }
    }

    private static void loadFile(){
        System.out.println("Enter the name of the file you wish to load: ");
        String fileName = reader.nextLine();

        try {

            File file = new File(fileName);
            MusicClipPlayer player = MusicClipFactory.makeMusicClipPlayer(fileName);

            musicPlayer.put(file.getName(), player);

            System.out.println("Successfully loaded file " + fileName + " as " + chopFileExtenstion(file.getPath()));

        } catch (LineUnavailableException e) {
            System.out.println("The line was unavailable for that file");
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An IO exception occurred");
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
