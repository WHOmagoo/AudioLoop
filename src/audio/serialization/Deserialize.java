package audio.serialization;


import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import java.io.*;

public class Deserialize {

    public static void deserialize() throws IOException {
        ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("file.ser"));
        XStream stream = new XStream(new StaxDriver());
    }
}
