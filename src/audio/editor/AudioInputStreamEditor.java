package audio.editor;

import javax.sound.sampled.*;
import java.io.*;

public class AudioInputStreamEditor{
    private byte[] originalData;


    public AudioInputStreamEditor(AudioInputStream audioStream, double speedModifier) throws IOException, LineUnavailableException {
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        byte[] b = new byte[2^16];
//        int read = 1;
//        while(read > -1){
//            read = audioStream.read(b);
//            if(read > 0){
//                out.write(b, 0, read);
//            }
//        }
//
//        originalData = out.toByteArray();
//        byte newData = new byte[originalData.length * speedModifier]
        int playBackSpeed = (int) speedModifier;
        int skip = playBackSpeed-1;
        System.out.println("Playback Rate: " + playBackSpeed);

        AudioInputStream ais = audioStream;
        AudioFormat af = audioStream.getFormat();

        int frameSize = af.getFrameSize();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[2^16];
        int read = 1;
        while( read>-1 ) {
            read = ais.read(b);
            if (read>0) {
                baos.write(b, 0, read);
            }
        }

        byte[] b1 = baos.toByteArray();
        byte[] b2 = new byte[b1.length/playBackSpeed];
        for (int ii=0; ii<b2.length/frameSize; ii++) {
            for (int jj=0; jj<frameSize; jj++) {
                b2[(ii*frameSize)+jj] = b1[(ii*frameSize*playBackSpeed)+jj];
            }
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(b2);
        AudioInputStream aisAccelerated =
                new AudioInputStream(bais, af, b2.length);
        Clip clip = AudioSystem.getClip();
        clip.open(aisAccelerated);
        clip.loop(2*playBackSpeed);
        clip.start();

    }
}
