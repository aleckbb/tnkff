package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task6Test {

    @Test
    @DisplayName("Число Капрекара")
    void test1() {
        int actual = Task6.countK(6174);
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Примеры из дз")
    void test2() {
        int actual = Task6.countK(6621);
        int expected = 5;
        assertEquals(expected, actual);
        actual = Task6.countK(6554);
        expected = 4;
        assertEquals(expected, actual);
        actual = Task6.countK(1234);
        expected = 3;
        assertEquals(expected, actual);
    }
}
