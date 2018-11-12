package test;

import audio.groupings.MusicClip;
import audio.groupings.MusicGrouping;
import audio.operators.*;
import audio.serialization.Serialize;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


class SerializationTest {

    @org.junit.jupiter.api.Test
    void serializeMusicGroupingEmpty() {
        MusicGrouping grouping = new MusicGrouping();
        String dingo = Serialize.serializeGroupings(grouping);
        System.out.println(dingo);
        assertEquals("<?xml version=\"1.0\" ?><audio.groupings.MusicGrouping><items></items></audio.groupings.MusicGrouping>", dingo);
    }

    @Test
    void serializeMusicGroupingsContainingMusicClips() throws FileNotFoundException {
        MusicClip hello = new MusicClip();
        MusicClip by = new MusicClip();
        MusicClip adele = new MusicClip();
        MusicGrouping grouping = new MusicGrouping(hello, by, adele);
        String dingo = Serialize.serializeGroupings(grouping);
        PrintWriter street = new PrintWriter(new File("/Users/mphannon/Documents/hello.xml"));
        street.print(dingo);
        street.close();
    }
}
