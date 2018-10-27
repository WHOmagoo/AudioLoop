package AudioEngine.LogicalOperators;

/**
 * Created by WHOmagoo on 10/27/2018.
 */
public abstract class Operator implements Operand{
    int presedence;

    public Operator(int presedence){
        this.presedence = presedence;
    }

    public int getPresedence(){
        return presedence;
    }

    public abstract boolean evaluate();
}
