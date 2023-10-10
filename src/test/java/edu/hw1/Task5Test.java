package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task5Test {

    @Test
    @DisplayName("Проверка является потомок числа полиндромом")
    void test1() {
        boolean actual = Task5.isPalindromeDescendant(123312);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Число нечетное")
    void test2() {
        boolean actual = Task5.isPalindromeDescendant(123);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Цифра")
    void test3() {
        boolean actual = Task5.isPalindromeDescendant(1);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Ноль")
    void test4() {
        boolean actual = Task5.isPalindromeDescendant(0);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Потомок не палиндром")
    void test5() {
        boolean actual = Task5.isPalindromeDescendant(1234);
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Нечетный потомок")
    void test6() {
        boolean actual = Task5.isPalindromeDescendant(123451);
        boolean expected = false;
        assertEquals(expected, actual);
    }
}
