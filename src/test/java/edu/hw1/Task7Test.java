package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task7Test {

    @Test
    @DisplayName("Примеры из дз")
    void test1() {
        int actual = Task7.rotateRight(8, 1);
        int expected = 4;
        assertEquals(expected, actual);
        actual = Task7.rotateLeft(16, 1);
        expected = 1;
        assertEquals(expected, actual);
        actual = Task7.rotateLeft(17, 2);
        expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Число отрицательное")
    void test2() {
        int actual = Task7.rotateLeft(-8, 1);
        int expected = -1;
        assertEquals(expected, actual);
        actual = Task7.rotateRight(-8, 1);
        expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Сдвиг отрицательный")
    void test3() {
        int actual = Task7.rotateLeft(8, -1);
        int expected = 4;
        assertEquals(expected, actual);
        actual = Task7.rotateRight(8, -1);
        expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Число и сдвиг отрицательные")
    void test4() {
        int actual = Task7.rotateLeft(-8, -1);
        int expected = -1;
        assertEquals(expected, actual);
        actual = Task7.rotateRight(-8, -1);
        expected = -1;
        assertEquals(expected, actual);
    }
}
