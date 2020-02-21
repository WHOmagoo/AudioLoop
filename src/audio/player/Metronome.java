package audio.player;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Timer;

public class Metronome implements IMusicClipPlayer{
    Clip clip;
    SourceDataLine line;

    public int loadInt(byte buff[], int offset, boolean bigEndian){
        if(bigEndian){
            int result = (0xFF & buff[offset]) << 24;
            result += (0xFF & buff[offset + 1])  << 16;
            result += (0xFF & buff[offset + 2]) << 8;
            result += (0xFF & buff[offset + 3]);
            return result;
        } else {
            int result = (0xFF & buff[offset + 3]) << 24;
            result += (0xFF & buff[offset + 2])  << 16;
            result += (0xFF & buff[offset + 1]) << 8;
            result += (0xFF & buff[offset]);
            return result;
        }
    }

    public int load16bits(byte buff[], int offset, boolean bigEndian){
        if(bigEndian){
            int result = (0xFF & buff[offset]) << 24;
            result += (0xFF & buff[offset + 1])  << 16;
            return result >> 16;
        } else {
            int result = (0xFF & buff[offset + 1]) << 24;
            result += (0xFF & buff[offset])  << 16;
            return result >> 16;
        }
    }

    public Metronome() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        int ia = loadInt(new byte[]{-4,2,-2,4}, 0, true);
        int ib = loadInt(new byte[]{0,1,2,-3}, 0, false);


        Mixer.Info[] infos = AudioSystem.getMixerInfo();

        AudioFormat audioFormat = new AudioFormat(8000, 16, 1, true, false);

        Mixer mixer = null;

        AudioInputStream stream = AudioSystem.getAudioInputStream(new File("HughTestTrack1.wav"));

        clip = AudioSystem.getClip();
        clip.open(stream);

        long start = System.currentTimeMillis();
//        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event) {
                if(event.getType() == LineEvent.Type.STOP) {
                    long finish = System.currentTimeMillis();
                    System.out.println("CLip playback took " + (finish - start) / 1000.0);
                }
            }
        });

        System.out.println("How long it should take " + clip.getMicrosecondLength() / 1000000.0);
        audioFormat = clip.getFormat();

        SourceDataLine line = null;

        for (var tmp :
                infos) {
            System.out.println("Mixer infos: " + tmp);
            System.out.println(tmp.getDescription());
            System.out.println(tmp.getVendor());

            Mixer m = AudioSystem.getMixer(tmp);

            m.open();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            DataLine.Info info2 = new DataLine.Info(Clip.class, audioFormat);
            Port.Info portInfo = Port.Info.SPEAKER;
//            boolean supportsPlayback = m.isLineSupported(portInfo);
            boolean supportsPlayback = m.isLineSupported(info) && m.isLineSupported(info2);
            System.out.println("\tSupports playback: " + supportsPlayback);

            m.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    System.out.println("Event " + event.getType() + " on " + event.getLine());
                }
            });

            if(supportsPlayback){
//                SourceDataLine tDL = (SourceDataLine) m.getLine(info);
//                Line tDL2 = m.getLine(info2);

                Clip c = AudioSystem.getClip(m.getMixerInfo());
                line = AudioSystem.getSourceDataLine(audioFormat, m.getMixerInfo());

                System.out.println("\tMax lines: " + m.getMaxLines(Port.Info.LINE_IN));
                System.out.println("\tSynchronization: " + m.isSynchronizationSupported(new Line[]{c, line}, false));
                mixer = m;
                break;
            }
        }

//        for (var tmp :
//                lineInfos) {
//            System.out.println("Target line:" + tmp);
//        }

//        System.out.println(m.getMaxLines(lineInfos[0]));
        System.out.println();

//        SourceDataLine line = (SourceDataLine) mixer.getLine(mixer.getSourceLineInfo()[0]);

        int totalToRead = 999999999;

//        FileInputStream loop1 = new FileInputStream(new File("clip1.wav"));
//        FileInputStream loop2 = new FileInputStream(new File("clip1Loop.wav"));
        FileInputStream loop1 = new FileInputStream(new File("HughTestTrack1.wav"));
        FileInputStream loop2 = new FileInputStream(new File("HughFroggyTest1.wav"));
//        FileInputStream loop1 = new FileInputStream(new File("HughFroggyTest1.wav"));
//        FileInputStream loop2 = new FileInputStream(new File("HughTestTrack1.wav"));

        //consume the header
//        fileInputStream.read(header, 0, header.length);

        long bytesPerSeond = (int) (line.getFormat().getFrameRate() * line.getFormat().getFrameSize());
        int numBytesToRead = 1024;
        byte b1[] = loop1.readNBytes(1048576);
        byte b2[] = loop2.readNBytes(1048576);


        int size = b1.length;
        if(size > b2.length){
            size = b2.length;
        }

        byte b[] = new byte[size];


        int subchunk1Size = (0xFF & b1[17]) | (0xFF & b1[18]) << 8 | (0xFF & b1[19] << 16) | (0xFF & b1[20]) << 24;

        int starting = 44;
        for (int i = starting; i < size - 2; i+= 2) {
//            char customByte = 83;
//            char reversed = reverse(customByte);
//            char other = reverse((char) 73);
//            int toInt = (int) reversed;
//            toInt <<= 24;
//            int afterShift = (toInt >> 16);
//            afterShift >>= 8;
//            byte reversedResult = (byte) afterShift;
//            byte result = reverse(reversedResult);
//
//            byte low = b1[i];
//            byte high = b1[i+1];
//
//            byte bLittle = reverse(low);
//            byte bBig = reverse(high);
//            int bLittleint = bLittle;
//            int bBigInt = bBig;
//            int twobytes = bBigInt << 24 | bLittleint << 16;
//
//            twobytes >>= 16;
//            twobytes >>= 8;
//
//            byte newBBig = (byte) twobytes;
//
//
//
//            byte resultLow = reverse(bLittle);
//            byte resultHigh = reverse(newBBig);

            char low1 =  toChar(b1[i]);
            char high1 = toChar(b1[i+1]);

            char low2 = toChar(b2[i]);
            char high2 = toChar(b2[i+1]);

//            low = reverse(low);
//            high = reverse(high);

            //We need to convert to 2's complement here instead of direct casting
            int combined1 = load16bits(b1, i, false);
            int combined2 = load16bits(b2, i, false);
//            int combined2 = 0;

            //Multiplication and Division
            int combined = combined1 + combined2;

//            if(combined - combined2 != combined1){
//                System.out.println("Overflow");
//            }

            int shifted = combined;
//            b[i] = b2[i];
            b[i] = (byte) shifted;
            shifted >>= 8;
            b[i+1] = (byte) shifted;
//            b[i+1] = b2[i+1];


//            if(b[i] != b1[i] || b[i+1] != b1[i+1]){
//                System.out.println("Error converting number");
//            }

//            int val1 = Integer.reverseBytes(b[i]) >> 24 | Integer.reverseBytes(b[1+i]) >> 16;

//            long val1 = Long.reverseBytes(b1[i]) >> 24 | Long.reverseBytes(b1[i+1]) >> 16 | Long.reverseBytes(b1[i+2]) >> 8 | Long.reverseBytes(b1[i+3]);
//
////            val1 = Integer.reverseBytes(val1) >> 16;
//            long val2 = Long.reverseBytes(b1[i]) >> 24 | Long.reverseBytes(b1[i+1]) >> 16 | Long.reverseBytes(b1[i+2]) >> 8 | Long.reverseBytes(b1[i+3]);
////            val2 = Integer.reverseBytes(val2) >> 16;
//            long result = val1;
//
//            result = Long.reverseBytes(result) >> 32;
//
//            b[i+3] = (byte) result;
//            result >>= 8;
//            b[i+2] = (byte) result;
//            result >>= 8;
//            b[i+1] = (byte) result;
//            result >>= 8;
//            b[i] = (byte) result;

        }

        byte blank[] = new byte[Math.toIntExact(bytesPerSeond)];

        mixer.open();
        line.open();



//        for (int i = 0; i < 5; i++) {
//            line.write(blank, 0, blank.length);
//        }

//        line.addLineListener(new LineListener() {
//            @Override
//            public void update(LineEvent event) {
//                if(event.getType() == LineEvent.Type.STOP){
//                    long finishTime = System.currentTimeMillis();
//
//                    System.out.println("Finished playback it took " + (finishTime - startTIme) / 1000.0);
//                }
//            }
//        });

        line.start();

        int total = starting;

        for (int i = 0; i < 5; i++) {

            long startTIme = System.currentTimeMillis();

            total = 44;

            while (total + numBytesToRead < b.length) {
                line.write(b, total, numBytesToRead);
                total += numBytesToRead;
            }

            //Write remaining frames to the line
            line.write(b, total, (b.length - total) / 4 * 4);

            long finishTIme = System.currentTimeMillis();

            System.out.println("Loop took " + (finishTIme - startTIme));

            total = 0;
        }

        line.stop();


        Thread.sleep(7250);
    }

    private char toChar(byte b){
        char result = 0;
        for (int i = 0; i < 8; i++) {
            if((b & 1) == 1){
                result |= 1 << i;
            }
            b >>= 1;
        }

        return result;
    }

    private byte reverse(byte b){
        byte result = 0;

        for(int i = 0; i < 8; i++){
            result <<= 1;
            if((b & 1) == 1){
                result+=1;
            }

            b >>= 1;
        }

        return result;
    }

    public long getTick(){
        return line.getMicrosecondPosition();
    }

    @Override
    public void play() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public long getLength() {
        return 0;
    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public void requestNotificationOnFinish(Notifiable obj) {

    }
}
