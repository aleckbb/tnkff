package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @Test
    @DisplayName("Число цифр в числе")
    void countDigits() {
        int actual = Task2.countDigits(0);
        int expected = 1;
        assertEquals(expected, actual);
        actual = Task2.countDigits(-3);
        expected = 1;
        assertEquals(expected, actual);
        actual = Task2.countDigits(3);
        expected = 1;
        assertEquals(expected, actual);
        actual = Task2.countDigits(123456789);
        expected = 9;
        assertEquals(expected, actual);
    }
}
