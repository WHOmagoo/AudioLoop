package AudioEngine.LogicalOperators;

public class Or extends BinaryOperator {

    public Or() {
        super(1);
    }

    @Override
    public boolean evaluate() {
        return getLeft().evaluate() || getRight().evaluate();
    }

}
