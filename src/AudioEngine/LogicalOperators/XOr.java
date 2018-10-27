package AudioEngine.LogicalOperators;

public class XOr extends BinaryOperator {

    public XOr() {
        super(2);
    }

    @Override
    public boolean evaluate() {
        return getLeft().evaluate() != getRight().evaluate();
    }

}
