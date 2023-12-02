package edu.hw7.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task4Test {
    @Test
    @DisplayName("Отклонение от числа pi меньше нормы")
    void test1() {
        // Given
        long numberOfIterations = 100_000_000;

        // Then
        assertTrue(Math.abs(Task4.multiThreadPI(numberOfIterations) - Math.PI) < 0.001);
    }
}
