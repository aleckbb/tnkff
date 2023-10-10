package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task2Test {

    @Test
    @DisplayName("Проверка нуля")
    void test1() {
        int actual = Task2.countDigits(0);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка отрицательного числа")
    void test2() {
        int actual = Task2.countDigits(-3);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка положительного числа")
    void test3() {
        int actual = Task2.countDigits(3);
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка числа с большим чисом цифр")
    void test4() {
        int actual = Task2.countDigits(123456789);
        int expected = 9;
        assertEquals(expected, actual);
    }
}
