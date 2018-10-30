package AudioEngine.Groupings;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Hugh on 10/29/2018.
 */
public class MusicGrouping {
    Set<Boolean> items = new HashSet<Boolean>();

    public MusicGrouping(Boolean ... items){
        this.items = new HashSet<Boolean>();
        for (Boolean item : items) {
            add(item);
        }
    }

    public void add(Boolean item){
        if(!items.contains(item)){
            items.add(item);
        }
    }

    public void remove(Boolean item){
        items.remove(item);
    }

    public void setChosen(boolean chosen){
        for (Boolean item: items) {
            item = chosen;
        }
    }

    public boolean evaluate(){
        for (Boolean item: items){
            if(!item){
                return false;
            }
        }

        return true;
    }


}
