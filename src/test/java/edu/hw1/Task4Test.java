package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

    @Test
    @DisplayName("Примеры из дз")
    void test1() {
        String actual = Task4.fixString("123456");
        String expected = "214365";
        assertEquals(expected, actual);
        actual = Task4.fixString("hTsii  s aimex dpus rtni.g");
        expected = "This is a mixed up string.";
        assertEquals(expected, actual);
        actual = Task4.fixString("badce");
        expected = "abcde";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Пустая строка")
    void test2() {
        String actual = Task4.fixString("");
        String expected = "";
        assertEquals(expected, actual);
    }
}
