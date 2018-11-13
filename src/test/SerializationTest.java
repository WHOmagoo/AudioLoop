package test;

import audio.groupings.MusicClip;
import audio.groupings.MusicGrouping;
import audio.operators.*;
import audio.serialization.Serialize;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;


class SerializationTest {

    @Test
    void serializeMusicGroupingEmpty() throws FileNotFoundException {
        MusicGrouping grouping = new MusicGrouping();
        String dingo = Serialize.serializeGroupings(grouping);
        System.out.println(dingo);
        assertEquals("<?xml version=\"1.0\" ?><musicGrouping><items></items></musicGrouping>", dingo);
    }

    @Test
    void serializeMusicGroupingsContainingMusicClips() throws FileNotFoundException {
        MusicClip hello = new MusicClip();
        MusicClip by = new MusicClip();
        MusicClip adele = new MusicClip();
        MusicGrouping grouping = new MusicGrouping(hello, by, adele);
        String dingo = Serialize.serializeGroupings(grouping);
        assertNotNull(dingo);
    }

}
