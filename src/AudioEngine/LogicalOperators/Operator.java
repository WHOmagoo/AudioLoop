package AudioEngine.LogicalOperators;

/**
 * Created by WHOmagoo on 10/27/2018.
 */
public abstract class Operator implements Operand{
    int precedence;

    public Operator(int presedence){
        this.precedence = presedence;
    }

    public int getPrecedence(){
        return precedence;
    }

    public abstract Operand getLeft();

    public abstract Operand getRight();

    public abstract boolean evaluate();

}
