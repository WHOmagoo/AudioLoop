package audio.groupings;

import audio.operators.Operand;

/**
 * Created by Hugh on 10/29/2018.
 */
public class MusicClip extends Music {
    boolean isChosen;

    public MusicClip() {
        isChosen = false;
    }

    public void setChosen(boolean isChosen){
        this.isChosen = isChosen;
    }

    public boolean evaluate() {
        return isChosen;
    }
}
