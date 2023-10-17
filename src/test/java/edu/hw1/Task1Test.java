package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task1Test {

    @Test
    @DisplayName("Два двоеточия")
    void test1() {
        int actual = Task1.minutesToSeconds("1::1");
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Минуты отрицательные")
    void test2() {
        int actual = Task1.minutesToSeconds("-1:1");
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Секунды отрицательные")
    void test3() {
        int actual = Task1.minutesToSeconds("1:-1");
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Минут нет")
    void test4() {
        int actual = Task1.minutesToSeconds(":11");
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Секунд нет")
    void test5() {
        int actual = Task1.minutesToSeconds("11:");
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Буквы в минутах")
    void test6() {
        int actual = Task1.minutesToSeconds("aa:1");
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Буквы в секундах")
    void test7() {
        int actual = Task1.minutesToSeconds("1:aa");
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("60 в секундах")
    void test8() {
        int actual = Task1.minutesToSeconds("1:60");
        int expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Нули в минутах и секундах")
    void test9() {
        int actual = Task1.minutesToSeconds("0:0");
        int expected = 0;
        assertEquals(expected, actual);
    }

}
