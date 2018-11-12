package audio.serialization;

import audio.groupings.MusicGrouping;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Writer;

public class Serialize {

    private static final XStream stream = new XStream(new StaxDriver());

    public static String serializeGroupings(MusicGrouping grouping) {

        return stream.toXML(grouping);
    }

}
