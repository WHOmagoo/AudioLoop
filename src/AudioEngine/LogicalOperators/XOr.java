package AudioEngine.LogicalOperators;

public class XOr extends BinaryOperator {

    public XOr() {
        super(2);
    }

    public boolean evaluate() {
        return getLeft().evaluate() != getRight().evaluate();
    }

}
