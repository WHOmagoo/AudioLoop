package test;

import AudioEngine.Groupings.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GroupingsTest {

    @Test
    void musicClipEvaluateNew() {
        MusicClip clip = new MusicClip();
        assertFalse(clip.evaluate());
    }

    @Test
    void musicClipSetChosen() {
        MusicClip clip = new MusicClip();
        clip.setChosen(true);
        assertTrue(clip.evaluate());
    }

    @Test
    void musicClipSetChosenFalse() {
        MusicClip clip = new MusicClip();
        clip.setChosen(false);
        assertFalse(clip.evaluate());
    }

    @Test
    void musicGroupingAddAll() {
        MusicClip dingo = new MusicClip();
        MusicClip dingus = new MusicClip();
        MusicGrouping group = new MusicGrouping(dingo, dingus);
        assertEquals(2, group.getSize());
    }

    @Test
    void musicGroupingAddOne() {
        MusicClip dingo = new MusicClip();
        MusicClip dingus = new MusicClip();
        MusicGrouping group = new MusicGrouping(dingo, dingus);
        MusicClip ding = new MusicClip();
        group.add(ding);
        assertEquals(3, group.getSize());
    }

    @Test
    void musicGroupingNoDuplicates() {
        MusicClip dingo = new MusicClip();
        MusicClip dingus = new MusicClip();
        MusicGrouping group = new MusicGrouping(dingo, dingus);
        group.add(dingo);
        assertEquals(2, group.getSize());
    }

    @Test
    void musicGroupingRemove() {
        MusicClip dingo = new MusicClip();
        MusicClip dingus = new MusicClip();
        MusicClip ding = new MusicClip();
        MusicGrouping group = new MusicGrouping(dingo, dingus);
        group.add(ding);
        group.remove(dingo);
        assertEquals(2, group.getSize());
    }

    @Test
    void musicGroupingAddNull() {
        MusicClip dingo = new MusicClip();
        MusicClip dingus = new MusicClip();
        MusicGrouping group = new MusicGrouping(dingo, dingus);
        MusicClip ding = new MusicClip();
        group.add(null);
        assertEquals(2, group.getSize());
    }

    @Test
    void musicGroupingContainsTrue() {
        MusicClip dingo = new MusicClip();
        MusicClip dingus = new MusicClip();
        MusicGrouping group = new MusicGrouping(dingo, dingus);
        MusicClip ding = new MusicClip();
        group.add(ding);
        assertTrue(group.contains(ding));
    }

    @Test
    void musicGroupingContainsFalse() {
        MusicClip dingo = new MusicClip();
        MusicClip dingus = new MusicClip();
        MusicGrouping group = new MusicGrouping(dingo, dingus);
        MusicClip ding = new MusicClip();
        assertFalse(group.contains(ding));
    }
}
