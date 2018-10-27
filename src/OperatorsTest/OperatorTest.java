package OperatorsTest;

import AudioEngine.LogicalOperators.And;
import AudioEngine.LogicalOperators.Operand;

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


}