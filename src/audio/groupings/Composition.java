package audio.groupings;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

public class Composition {

    int size;
    private Hashtable<Integer, CueOptions> options;

    public Composition(int size){
        options = new Hashtable<>();
        this.size = size;
    }

    public boolean addCueOption(int index, CueOptions option){
        if(option == null){
            throw new NullPointerException("The supplied CueOption was null");
        }

        if(index > size || index < 0){
            throw new IndexOutOfBoundsException("Index was outside of range");
        }

        boolean result = !options.containsKey(index);

        if(result){
            options.put(index, option);
        }

        return result;
    }

    public void removeCueOption(int index){
        options.remove(index);
    }

    public void resize(int newSize){
        size = newSize;
    }
}
