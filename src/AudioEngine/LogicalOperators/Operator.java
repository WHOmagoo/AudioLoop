package AudioEngine.LogicalOperators;

/**
 * Created by WHOmagoo on 10/27/2018.
 */

public abstract class Operator implements Operand
{
    int precedence;

     public Operator(int precedence){
        this.precedence = precedence;
    }
    public int getPrecedence(){
        return precedence;
    }
}
