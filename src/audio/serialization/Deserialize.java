package audio.serialization;


import audio.groupings.MusicGrouping;
import audio.operators.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.*;
import java.util.ArrayList;

public class Deserialize {

    public static ArrayList<MusicGrouping> deserializeMusicGrouping(ArrayList<String> groupingXMLs) {
        XStream stream = new XStream(new StaxDriver());
        ArrayList<MusicGrouping> groupings = new ArrayList<>();
        for (String groupingXML : groupingXMLs) {
            MusicGrouping grouping = (MusicGrouping) stream.fromXML(groupingXML);
            groupings.add(grouping);
        }
        return groupings;
    }

    public static And deserializeAndOperator(String andXML) {
        XStream stream = new XStream(new StaxDriver());
        return (And) stream.fromXML(andXML);
    }

    public static Or deserializeOrOperator(String andXML) {
        XStream stream = new XStream(new StaxDriver());
        return (Or) stream.fromXML(andXML);
    }

    public static XOr deserializeXOrOperator(String andXML) {
        XStream stream = new XStream(new StaxDriver());
        return (XOr) stream.fromXML(andXML);
    }

    public static Not deserializeNotOperator(String andXML) {
        XStream stream = new XStream(new StaxDriver());
        return (Not) stream.fromXML(andXML);
    }
}
