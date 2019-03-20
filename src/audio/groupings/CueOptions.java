package audio.groupings;

import audio.operators.Operand;
import audio.operators.Operator;

import java.util.HashMap;
import java.util.Map;

public class CueOptions {
    HashMap<Music, Operand> options;

    public CueOptions(Map<Music, Operand> collection){
        this();
        options.putAll(collection);
    }

    public CueOptions(){
        options = new HashMap<Music, Operand>();
    }

    public void add(Music option, Operator condition){
        options.put(option, condition);
    }

    public void remove(Music option){
        options.remove(option);
    }

    public int optionsCount(){
        return options.size();
    }

    public HashMap<Music, Operand> getOptions(){
        return options;
    }
}
