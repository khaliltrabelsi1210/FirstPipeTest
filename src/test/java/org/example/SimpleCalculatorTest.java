package org.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SimpleCalculatorTest {
    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAddition() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    public void testSubtraction() {
        assertEquals(2, calculator.subtract(5, 3));
    }

    @Test
    public void testMultiplication() {
        assertEquals(15, calculator.multiply(3, 5));
    }

    @Test
    public void testDivision() {
        assertEquals(2, calculator.divide(6, 3));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivisionByZero() {
        System.out.println("kavi");
        calculator.divide(0, 0);
    }
}
