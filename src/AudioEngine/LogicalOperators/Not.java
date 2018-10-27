package AudioEngine.LogicalOperators;

public class Not extends UnaryOperator{

    public Not(int precedence) {
        super(precedence);
    }

    @Override
    public boolean evaluate() {
        return !getRight().evaluate();
    }


}
