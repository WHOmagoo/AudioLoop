package AudioEngine.LogicalOperators;

public class And extends BinaryOperator{

    public And() {
        super(3);
    }

    @Override
    public boolean evaluate() {
        return getLeft().evaluate() && getRight().evaluate();
    }

}

