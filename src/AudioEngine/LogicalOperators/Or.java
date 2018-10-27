package AudioEngine.LogicalOperators;

public class Or extends BinaryOperator {

    public Or(int presedence) {
        super(presedence);
    }

    @Override
    public boolean eval() {
        return false;
    }

}
