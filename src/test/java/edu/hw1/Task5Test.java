package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task5Test {

    @Test
    @DisplayName("Проверка является ли число или его потомок полиндромом")
    void isPalindromeDescendant() {
        boolean actual = Task5.isPalindromeDescendant(123312);
        boolean expected = true;
        assertEquals(expected, actual);
        actual = Task5.isPalindromeDescendant(123);
        expected = false;
        assertEquals(expected, actual);
        actual = Task5.isPalindromeDescendant(1);
        expected = true;
        assertEquals(expected, actual);
        actual = Task5.isPalindromeDescendant(0);
        expected = true;
        assertEquals(expected, actual);
        actual = Task5.isPalindromeDescendant(1234);
        expected = false;
        assertEquals(expected, actual);
        actual = Task5.isPalindromeDescendant(123451);
        expected = false;
        assertEquals(expected, actual);

    }
}
