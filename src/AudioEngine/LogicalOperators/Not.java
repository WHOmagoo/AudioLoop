package AudioEngine.LogicalOperators;

public class Not extends UnaryOperator{

    public Not() {
        super(Integer.MAX_VALUE);
    }

    public boolean evaluate() {
        return !getChild().evaluate();
    }


}
