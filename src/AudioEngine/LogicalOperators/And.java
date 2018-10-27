package AudioEngine.LogicalOperators;

public class And extends BinaryOperator{

    public And(int precedence) {
        super(precedence);
    }

    @Override
    public boolean evaluate() {
        return false;
    }

}

