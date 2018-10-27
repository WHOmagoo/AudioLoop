package OperatorsTest;

import AudioEngine.LogicalOperators.And;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by WHOmagoo on 10/27/2018.
 */
class OperatorTest {
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