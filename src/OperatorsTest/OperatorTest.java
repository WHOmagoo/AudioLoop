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

}