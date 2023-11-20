package edu.hw7.Task2;

import java.math.BigInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task2Test {

    @Test
    @DisplayName("Факториал числа считается правильно")
    void test1() {
        // Given
        int num = 5;

        // Then
        assertEquals(new BigInteger("120"), Task2.factorialOfNumber(num));
    }

    @Test
    @DisplayName("Факториал числа 1")
    void test2() {
        // Given
        int num = 1;

        // Then
        assertEquals(new BigInteger("1"), Task2.factorialOfNumber(num));
    }

    @Test
    @DisplayName("Факториал числа 0")
    void test3() {
        // Given
        int num = 0;

        // Then
        assertEquals(new BigInteger("1"), Task2.factorialOfNumber(num));
    }

    @Test
    @DisplayName("Факториал отрицательного числа")
    void test4() {
        // Given
        int num = -1;

        // Then
        assertEquals(new BigInteger("1"), Task2.factorialOfNumber(num));
    }

    @Test
    @DisplayName("Факториал правильно считается даже для больших чисел")
    void test5() {
        // Given
        int num = 30;

        // Then
        assertEquals(new BigInteger("265252859812191058636308480000000"), Task2.factorialOfNumber(num));
    }
}
