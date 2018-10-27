package AudioEngine.LogicalOperators;

/**
 * Created by WHOmagoo on 10/27/2018.
 */
public abstract class BinaryOperator extends Operator{
    Operand left;
    Operand right;

    public BinaryOperator(int precedence) {
        super(precedence);
    }

    public Operand getLeft(){
        return left;
    }

    public Operand getRight(){
        return right;
    }

    public void setRight(Operand right){
        this.right = right;
    }

    public void setLeft(Operand left){
        this.left = left;
    }
}
