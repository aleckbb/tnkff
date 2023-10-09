package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

    @Test
    @DisplayName("Исправление строки")
    void fixString() {
        String actual = Task4.fixString("123456");
        String expected = "214365";
        assertEquals(expected, actual);
        actual = Task4.fixString("hTsii  s aimex dpus rtni.g");
        expected = "This is a mixed up string.";
        assertEquals(expected, actual);
        actual = Task4.fixString("badce");
        expected = "abcde";
        assertEquals(expected, actual);
        actual = Task4.fixString("");
        expected = "";
        assertEquals(expected, actual);
    }
}
