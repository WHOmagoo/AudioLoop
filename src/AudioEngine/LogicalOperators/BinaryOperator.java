package AudioEngine.LogicalOperators;

/**
 * Created by WHOmagoo on 10/27/2018.
 */
public abstract class BinaryOperator extends Operator{
    Operand left;
    Operand right;

    public BinaryOperator(int precedence) {
        super(precedence);
    }
}
