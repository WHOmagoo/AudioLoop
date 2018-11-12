package audio.groupings;

import audio.operators.Operand;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugh on 10/29/2018.
 */
public class MusicGrouping extends Music implements Operand {
    Set<MusicClip> items = new HashSet<MusicClip>();

    public MusicGrouping(MusicClip ... items){
        this.items = new HashSet<MusicClip>();
        addAll(items);
    }

    public void add(MusicClip item){
        //TODO Throw an error or silently not add a null
        if (item != null && !items.contains(item)) {
            items.add(item);
        }
    }

    public void addAll(MusicClip ... items){
        for(MusicClip item : items){
            add(item);
        }
    }

    public int getSize() {
        return items.size();
    }

    public void remove(MusicClip item){
        items.remove(item);
    }

    public boolean contains(MusicClip item){
        return items.contains(item);
    }

    public void setChosen(MusicClip chosen){
        for (MusicClip item: items) {
            item = chosen;
        }
    }

    public boolean evaluate(){
        for (MusicClip item: items){
            if(!item.evaluate()){
                return false;
            }
        }

        return true;
    }




}
