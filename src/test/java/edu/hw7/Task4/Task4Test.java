package edu.hw7.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task4Test {

    @Test
    @DisplayName("Многопоточное решение быстрее, чем однопоточное")
    void test1() {
        // Given
        long numberOfIterations = 1_000_000_000;

        // When
        var first = System.nanoTime();
        Task4.oneThreadPI(numberOfIterations);
        var expected = System.nanoTime() - first;
        first = System.nanoTime();
        Task4.multiThreadPI(numberOfIterations);
        var actual = System.nanoTime() - first;

        // Then
        assertTrue(actual < expected);
    }

    @Test
    @DisplayName("Отклонение от числа pi меньше нормы")
    void test2() {
        // Given
        long numberOfIterations = 100_000_000;

        // Then
        assertTrue(Math.abs(Task4.multiThreadPI(numberOfIterations) - Math.PI) < 0.001);
    }
}
