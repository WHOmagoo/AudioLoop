package audio.player;
import java.util.ArrayList;
import java.util.List;

public class ManyClipsPlayer implements Notifiable {
    private ArrayList<IMusicClipPlayer> clips;
    private volatile int waitCount;


    public ManyClipsPlayer(){
        clips = new ArrayList<>();
        waitCount = 0;
    }

    public ManyClipsPlayer(List<IMusicClipPlayer> clips){
        this.clips = new ArrayList<>(clips);
    }

    public void add(IMusicClipPlayer clip){
        if(clip != null && !clips.contains(clip)){
            clips.add(clip);
        }
    }

    public void play(){
        for (IMusicClipPlayer clip : clips){
            clip.play();
            clip.requestNotificationOnFinish(this);
            waitCount++;
        }
    }

    @Override
    public synchronized void notifyObject(Object sender) {
        waitCount--;
        if(waitCount <= 0){
            play();
        }
    }
}
