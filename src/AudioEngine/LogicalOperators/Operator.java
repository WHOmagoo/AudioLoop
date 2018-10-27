package AudioEngine.LogicalOperators;

/**
 * Created by WHOmagoo on 10/27/2018.
 */

public abstract class Operator implements Operand{
    private int precedence;

    public Operator(int precedence){
        this.precedence = precedence;
    }

    public int getPrecedence(){
        return precedence;
    }

    public abstract Operand getLeft();

    public abstract Operand getRight();

    public abstract boolean evaluate();
}
