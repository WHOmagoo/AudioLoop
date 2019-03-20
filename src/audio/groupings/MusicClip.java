package audio.groupings;

/**
 * Created by Hugh on 10/29/2018.
 */
public class MusicClip extends Music {
    boolean isChosen;

    public MusicClip(String name) {
        super(name);
        isChosen = false;
    }

    public MusicClip() {
        this("NotSpecified");
    }

    public void setChosen(boolean isChosen){
        this.isChosen = isChosen;
    }

    public boolean evaluate() {
        return isChosen;
    }
}
