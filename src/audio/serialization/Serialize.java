package audio.serialization;

import audio.groupings.MusicClip;
import audio.groupings.MusicGrouping;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.*;

public class Serialize {



    public static String serializeGroupings(MusicGrouping grouping) {
        XStream stream = new XStream(new StaxDriver());
        stream.alias("musicGrouping", MusicGrouping.class);
        stream.alias("musicClip", MusicClip.class);
        return stream.toXML(grouping);
    }

}
