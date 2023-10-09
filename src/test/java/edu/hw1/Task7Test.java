package edu.hw1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task7Test {

    @Test
    void rotateLeft() {
        int actual = Task7.rotateLeft(8, 1);
        int expected = 1;
        assertEquals(expected, actual);
        actual = Task7.rotateLeft(16, 1);
        expected = 1;
        assertEquals(expected, actual);
        actual = Task7.rotateLeft(17, 2);
        expected = 6;
        assertEquals(expected, actual);
        actual = Task7.rotateLeft(-8, 1);
        expected = -1;
        assertEquals(expected, actual);
        actual = Task7.rotateLeft(8, -1);
        expected = 4;
        assertEquals(expected, actual);
        actual = Task7.rotateLeft(-8, -1);
        expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    void rotateRight() {
        int actual = Task7.rotateRight(8, 1);
        int expected = 4;
        assertEquals(expected, actual);
        actual = Task7.rotateRight(16, 1);
        expected = 8;
        assertEquals(expected, actual);
        actual = Task7.rotateRight(17, 2);
        expected = 12;
        assertEquals(expected, actual);
        actual = Task7.rotateRight(-8, 1);
        expected = -1;
        assertEquals(expected, actual);
        actual = Task7.rotateRight(8, -1);
        expected = 1;
        assertEquals(expected, actual);
        actual = Task7.rotateRight(-8, -1);
        expected = -1;
        assertEquals(expected, actual);
    }
}
