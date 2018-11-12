package audio.serialization;

import javax.xml.namespace.NamespaceContext;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
public class Deserialize {

    public static void deserialize() throws IOException {
        ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("file.ser"));

        while(true) {

        }
    }
}
