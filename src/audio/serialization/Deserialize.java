package audio.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
public class Deserialize {

    public static void deserialize() throws IOException {
        ObjectInputStream oIS = new ObjectInputStream(new FileInputStream("file.ser"));

        while(true) {

        }
    }
}
