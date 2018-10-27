package AudioEngine.LogicalOperators;

public class Not extends UnaryOperator{

    public Not(int presedence) {
        super(presedence);
    }

    @Override
    public boolean eval() {
        return false;
    }

}
