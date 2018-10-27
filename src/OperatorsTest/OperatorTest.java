package OperatorsTest;

import AudioEngine.LogicalOperators.*;

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
        public boolean evaluate(){
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
    void getPrecedenceAnd() {
        Operator op = new And();
        assertEquals(3, op.getPrecedence());
    }

    @org.junit.jupiter.api.Test
    void getPrecedenceOr() {
        Operator op = new Or();
        assertEquals(1, op.getPrecedence());
    }

    @org.junit.jupiter.api.Test
    void getPrecedenceXOr() {
        Operator op = new XOr();
        assertEquals(2, op.getPrecedence());
    }

    @org.junit.jupiter.api.Test
    void getPrecedenceNot() {
        Operator op = new Not();
        assertEquals(Integer.MAX_VALUE, op.getPrecedence());
    }

    @org.junit.jupiter.api.Test
    void setChildrenLeftReal() {
        And and = new And();
        ConcreteOperand op = new ConcreteOperand(true);
        and.setLeft(op);
        assertEquals(op, and.getLeft());
    }

    @org.junit.jupiter.api.Test
    void setChildrenLeftNull() {
        And and = new And();
        and.setLeft(null);
        assertNull(and.getLeft());
    }

    @org.junit.jupiter.api.Test
    void setChildrenRightReal() {
        And and = new And();
        ConcreteOperand op = new ConcreteOperand(true);
        and.setRight(op);
        assertEquals(op, and.getRight());
    }

    @org.junit.jupiter.api.Test
    void setChildrenRightNull() {
        And and = new And();
        and.setRight(null);
        assertNull(and.getRight());
    }


    @org.junit.jupiter.api.Test
    void trueXOrTrue(){
        ConcreteOperand left = new ConcreteOperand(true);
        ConcreteOperand right = new ConcreteOperand(true);

        BinaryOperator op = new XOr();
        op.setLeft(left);
        op.setRight(right);
        assertFalse(op.evaluate());
    }

    @org.junit.jupiter.api.Test
    void trueXOrFalse(){
        ConcreteOperand left = new ConcreteOperand(true);
        ConcreteOperand right = new ConcreteOperand(false);

        BinaryOperator op = new XOr();
        op.setLeft(left);
        op.setRight(right);
        assertTrue(op.evaluate());
    }

    @org.junit.jupiter.api.Test
    void falseXOrFalse(){
        ConcreteOperand left = new ConcreteOperand(false);
        ConcreteOperand right = new ConcreteOperand(false);

        BinaryOperator op = new XOr();
        op.setLeft(left);
        op.setRight(right);
        assertFalse(op.evaluate());
    }

    @org.junit.jupiter.api.Test
    void falseXOrTrue(){
        ConcreteOperand left = new ConcreteOperand(false);
        ConcreteOperand right = new ConcreteOperand(true);

        BinaryOperator op = new XOr();
        op.setLeft(left);
        op.setRight(right);
        assertTrue(op.evaluate());
    }

    @org.junit.jupiter.api.Test
    void negateTrue(){
        ConcreteOperand child = new ConcreteOperand(true);
        UnaryOperator op = new Not();
        op.setChild(child);
        assertFalse(op.evaluate());
    }

    @org.junit.jupiter.api.Test
    void negateFalse(){
        ConcreteOperand child = new ConcreteOperand(false);
        UnaryOperator op = new Not();
        op.setChild(child);
        assertTrue(op.evaluate());
    }

}