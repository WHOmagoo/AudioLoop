package AudioEngine.LogicalOperators;

public class XOr extends BinaryOperator {

    public XOr(int precedence) {
        super(precedence);
    }

    @Override
    public boolean evaluate() {
        return false;
    }

}
