package audio.operators;

import java.io.Serializable;

/**
 * Created by WHOmagoo on 10/27/2018.
 */

public abstract class Operator implements Operand, Serializable {
    int precedence;

     public Operator(int precedence){
        this.precedence = precedence;
    }
    public int getPrecedence(){
        return precedence;
    }


}
