package AudioEngine.LogicalOperators;

public class Not extends UnaryOperator{

    public Not() {
        super(Integer.MAX_VALUE);
    }

    @Override
    public boolean evaluate() {
        return !getRight().evaluate();
    }


}
