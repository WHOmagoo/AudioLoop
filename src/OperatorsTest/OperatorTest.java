package OperatorsTest;

import AudioEngine.LogicalOperators.And;
import AudioEngine.LogicalOperators.Operand;
import AudioEngine.LogicalOperators.XOr;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by WHOmagoo on 10/27/2018.
 */
class OperatorTest {
    private class ConcreteOperand implements Operand{
        boolean bool;

        public ConcreteOperand(boolean bool){
            this.bool = bool;
        }

        @Override
        public boolean evaluate() {
            return bool;
        }
    }

    @org.junit.jupiter.api.Test
    void ConcreteOperandEvaluateTrue() {
        ConcreteOperand op = new ConcreteOperand(true);
        assertTrue(op.evaluate());
    }

    @org.junit.jupiter.api.Test
    void ConcreteOperandEvaluateFalse() {
        ConcreteOperand op = new ConcreteOperand(false);
        assertFalse(op.evaluate());
    }

    @org.junit.jupiter.api.Test
    void getPrecedence() {
        And and = new And();
        assertEquals(3, and.getPrecedence());
    }

    @org.junit.jupiter.api.Test
    void getLeft() {

    }

    @org.junit.jupiter.api.Test
    void getRight() {

    }

    @org.junit.jupiter.api.Test
    void evaluate() {

    }

    @org.junit.jupiter.api.Test
    void trueXOrTrue(){
        ConcreteOperand left = new ConcreteOperand(true);
        ConcreteOperand right = new ConcreteOperand(true);

        XOr xor = new XOr();
        xor.setLeft(left);
        xor.setRight(right);
        assertFalse(xor.evaluate());
    }

    @org.junit.jupiter.api.Test
    void trueXOrFalse(){
        ConcreteOperand left = new ConcreteOperand(true);
        ConcreteOperand right = new ConcreteOperand(false);

        XOr xor = new XOr();
        xor.setLeft(left);
        xor.setRight(right);
        assertTrue(xor.evaluate());
    }

    @org.junit.jupiter.api.Test
    void falseXOrFalse(){
        ConcreteOperand left = new ConcreteOperand(false);
        ConcreteOperand right = new ConcreteOperand(false);

        XOr xor = new XOr();
        xor.setLeft(left);
        xor.setRight(right);
        assertFalse(xor.evaluate());
    }

    @org.junit.jupiter.api.Test
    void falseXOrTrue(){
        ConcreteOperand left = new ConcreteOperand(false);
        ConcreteOperand right = new ConcreteOperand(true);

        XOr xor = new XOr();
        xor.setLeft(left);
        xor.setRight(right);
        assertTrue(xor.evaluate());
    }

}