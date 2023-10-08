package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    @Test
    @DisplayName("Вложенность массивов в другой массив")
    void isNestable() {
        int[] a1 = {0, 0, 0}, a2 = {0, 0};
        boolean actual = Task3.isNestable(a1, a2);
        boolean expected = false;
        assertEquals(expected, actual);

        a1 = new int[] {9, 9, 8};
        a2 = new int[] {8, 9};
        actual = Task3.isNestable(a1, a2);
        expected = false;
        assertEquals(expected, actual);

        a1 = new int[] {1, 2, 3, 4};
        a2 = new int[] {2, 3};
        actual = Task3.isNestable(a1, a2);
        expected = false;
        assertEquals(expected, actual);

        a1 = new int[] {3, 1};
        a2 = new int[] {4, 0};
        actual = Task3.isNestable(a1, a2);
        expected = true;
        assertEquals(expected, actual);

        a1 = new int[] {1, 2, 3, 4};
        a2 = new int[] {0, 6};
        actual = Task3.isNestable(a1, a2);
        expected = true;
        assertEquals(expected, actual);

        a1 = new int[] {};
        a2 = new int[] {0, 6};
        actual = Task3.isNestable(a1, a2);
        expected = false;
        assertEquals(expected, actual);
    }
}
