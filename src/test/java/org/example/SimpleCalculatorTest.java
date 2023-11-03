package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCalculatorTest {
    @Test
    void twoPlusTwoShouldEqualFour(){
        var calculator=new SimpleCalculator();
        assertTrue( calculator.add(2,2)==4);

    }


}