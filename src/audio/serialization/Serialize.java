package audio.serialization;

import audio.groupings.MusicClip;
import audio.groupings.MusicGrouping;
import audio.operators.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.*;

public class Serialize {

    /**
     * Serializes a MusicGrouping object to an XML file.
     * @param grouping the Music Grouping Object
     * @return The string of the XML text.
     */
    public static String serializeGroupings(MusicGrouping grouping) {
        XStream stream = new XStream(new StaxDriver());
        stream.alias("musicGrouping", MusicGrouping.class);
        stream.alias("musicClip", MusicClip.class);
        String serialization = stream.toXML(grouping);
        System.out.println(serialization);
        return serialization;
    }

    public static String serializeAndOperator(And and) {
        XStream stream = new XStream(new StaxDriver());
        stream.alias("andOperator", And.class);
        String serialization = stream.toXML(and);
        System.out.println(serialization);
        return serialization;
    }

    public static String serializeOrOperator(Or or) {
        XStream stream = new XStream(new StaxDriver());
        stream.alias("orOperator", Or.class);
        String serialization = stream.toXML(or);
        System.out.println(serialization);
        return serialization;
    }

    public static String serializeXOrOperator(XOr xOr) {
        XStream stream = new XStream(new StaxDriver());
        stream.alias("xOrOperator", XOr.class);
        String serialization = stream.toXML(xOr);
        System.out.println(serialization);
        return serialization;
    }

    public static String serializeNotOperator(Not not) {
        XStream stream = new XStream(new StaxDriver());
        stream.alias("notOperator", Not.class);
        String serialization = stream.toXML(not);
        System.out.println(serialization);
        return serialization;
    }

}
