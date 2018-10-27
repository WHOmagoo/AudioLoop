package AudioEngine.LogicalOperators;

public class XOr extends BinaryOperator {

    public XOr(int presedence) {
        super(presedence);
    }

    @Override
    public boolean eval() {
        return false;
    }

}
