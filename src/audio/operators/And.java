package audio.operators;

public class And extends BinaryOperator{

    public And() {
        super(3);
    }

    public boolean evaluate() {
        return getLeft().evaluate() && getRight().evaluate();
    }

}

