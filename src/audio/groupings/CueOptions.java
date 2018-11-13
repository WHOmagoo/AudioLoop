package audio.groupings;

import java.util.Collection;
import java.util.TreeSet;

public class CueOptions {
    TreeSet<Music> options;

    public CueOptions(Collection<Music> collection){
        this();
        options.addAll(collection);
    }

    public CueOptions(){
        options = new TreeSet<Music>();
    }

    public void add(Music option){
        options.add(option);
    }

    public void remove(Music option){
        options.remove(option);
    }

    public int optionsCount(){
        return options.size();
    }
}
