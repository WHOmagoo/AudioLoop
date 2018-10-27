package AudioEngine.LogicalOperators;

/**
 * Created by WHOmagoo on 10/27/2018.
 */
public abstract class UnaryOperator extends Operator{
    Operand child;

    public UnaryOperator(int precedence) {
        super(precedence);
    }

    public Operand getRight(){
        return child;
    }

    public Operand getLeft(){
        return null;
    }
}
