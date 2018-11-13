package audio.groupings;

import audio.operators.Operand;

public abstract class Music implements Operand {
    String name;
    boolean isPlaying;

    public Music(String name){
        this.name = name;
    }

    public void setPlaying(boolean playing){
        isPlaying = playing;
    }

    public boolean isPlaying(){
        return isPlaying;
    }
}
