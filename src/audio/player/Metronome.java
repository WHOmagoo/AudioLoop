package audio.player;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Timer;

public class Metronome implements IMusicClipPlayer{
    Clip clip;
    SourceDataLine line;

    private String getId(byte buff[], int startingIndex){
        return new String(new char[]{(char) buff[startingIndex], (char) buff[startingIndex + 1], (char) buff[startingIndex + 2], (char) buff[startingIndex + 3]});
    }

    private long getLong(byte buff[], int startingIndex){
        long result = (0xFF & buff[startingIndex]) << 24;

        result |= (0xFF & buff[startingIndex + 1]) << 16 ;
        result |=  (0xFF & buff[startingIndex + 2]) << 8 ;
        result |=  (0xFF & buff[startingIndex + 3]);

        return result;
    }

    public Metronome() throws IOException, UnsupportedAudioFileException, LineUnavailableException, InterruptedException {
        Mixer.Info[] infos = AudioSystem.getMixerInfo();

        AudioFormat audioFormat = new AudioFormat(8000, 16, 1, true, false);

        Mixer mixer = null;

        AudioInputStream stream = AudioSystem.getAudioInputStream(new File("01 Jingle Bells.aiff"));

        clip = AudioSystem.getClip();
        clip.open(stream);

        long start = System.currentTimeMillis();
//        clip.loop(Clip.LOOP_CONTINUOUSLY);
//        clip.addLineListener(new LineListener() {
//            @Override
//            public void update(LineEvent event) {
//                if(event.getType() == LineEvent.Type.STOP) {
//                    long finish = System.currentTimeMillis();
//                    System.out.println("CLip playback took " + (finish - start) / 1000.0);
//                }
//            }
//        });

        System.out.println("How long it should take " + clip.getMicrosecondLength() / 1000000.0);
        audioFormat = clip.getFormat();

        System.out.println("Format\n" + audioFormat);

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
                SourceDataLine sDL = AudioSystem.getSourceDataLine(audioFormat, m.getMixerInfo());

                System.out.println("\tMax lines: " + m.getMaxLines(Port.Info.LINE_IN));
                System.out.println("\tSynchronization: " + m.isSynchronizationSupported(new Line[]{c, sDL}, false));
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

        SourceDataLine line = (SourceDataLine) mixer.getLine(mixer.getSourceLineInfo()[0]);

        int totalToRead = 999999999;

        FileInputStream loop1 = new FileInputStream(new File("01 Jingle Bells.aiff"));
        FileInputStream loop2 = new FileInputStream(new File("01 Jingle Bells.aiff"));

        //consume the header
//        fileInputStream.read(header, 0, header.length);

        long bytesPerSeond = (int) (line.getFormat().getFrameRate() * line.getFormat().getFrameSize());
        int numBytesToRead = 1024;
        byte b1[] = loop1.readAllBytes();
        byte b2[] = loop2.readAllBytes();


        int size = b1.length;
        if(size > b2.length){
            size = b2.length;
        }

        byte b[] = new byte[size];

        String chkId = getId(b1, 0);
        long chunkSize = getLong(b1, 4);
        chkId = getId(b1, 8);

        chkId = getId(b1, 12);
        chunkSize = getLong(b1, 16);

//        chkId = getId(b1, 17 + (int) chunkSize);

        int soundCOunt = 0;
        int soundStart = 0;
        long soundSize = 0;

        for(int i = 0; i < b1.length - 4; i++){
            chkId = getId(b1, i);
            if(chkId.equalsIgnoreCase("SSND")){
                soundStart = i + 16;
                System.out.println("Found it at " + i);
                soundSize = getLong(b1, i + 4);
                long offset = getLong(b1, i + 8);
                long sndsize = getLong(b1, i + 12);
                System.out.println(soundSize + " " + offset + " " + sndsize);
                soundCOunt++;
                i+= 15 + sndsize;
                break;
            }
        }

        int subchunk1Size = b1[17] << 24 | b1[18] << 16 | b1[19] << 8 | b1[20];

        int starting = 44;
        for (int i = soundStart; i < soundSize; i+= 4) {
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

            char channel1msb =  toChar(b1[i]);
            char channel1lsb = toChar(b1[i+1]);

            char channel2msb = toChar(b1[i+2]);
            char channel2lsb = toChar(b1[i+3]);
            /*Worked on wav*/
//            char low2 = toChar(reverse(b2[i]));
//            char high2 = toChar(reverse(b2[i+1]));

//            low = reverse(low);
//            high = reverse(high);

            int channel1Data = channel1msb << 8 | channel1lsb;
            int channel2Data = channel2msb << 8 | channel2lsb;
            b[i - soundStart + 0] = (b1[i]);
            b[i - soundStart + 1] = (b1[i+1]);
            b[i - soundStart + 2] = (b1[i+2]);
            b[i - soundStart + 3] = (b1[i+3]);




            //We need to convert to 2's complement here instead of direct casting
//            int combined1 = (low1 << 16) | (high1 << 24);
//            int combined2 = (low2 << 16) | (high2 << 24);
//            int combined2 = 0;

//            int combined = (combined1 + combined2) - combined2;

//            if(combined - combined2 != combined1){
//                System.out.println("Overflow");
//            }

            /* Worked for wav
            int shifted = combined >> 16;
            b[i] = reverse((byte) shifted);
            shifted >>= 8;
            b[i+1] = reverse((byte) shifted);
*/
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

        int total = 0;

        for (int i = 0; i < 5; i++) {

            long startTIme = System.currentTimeMillis();

            while (total + numBytesToRead < b.length) {
                line.write(b, total, numBytesToRead);
                total += numBytesToRead;
            }

            //Write remaining frames to the line
            //line.write(b, total, (b.length - total) / 4 * 4);

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
