package AudioEngine.LogicalOperators;

public class And extends BinaryOperator{

    public And(int presedence) {
        super(presedence);
    }

    @Override
    public boolean eval() {
        return false;
    }

}

