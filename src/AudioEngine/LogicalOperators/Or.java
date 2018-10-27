package AudioEngine.LogicalOperators;

public class Or extends BinaryOperator {

    public Or(int precedence) {
        super(precedence);
    }

    @Override
    public boolean evaluate() {
        return getLeft().evaluate() || getRight().evaluate();
    }

}
