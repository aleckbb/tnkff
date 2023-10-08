package edu.hw1;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Task1Test {

    @Test
    @DisplayName("Конвертация времени в секунды")
    void minuteToSeconds() {
        int actual = Task1.minutesToSeconds("1::1");
        int expected = -1;
        assertEquals(expected, actual);
        actual = Task1.minutesToSeconds("-1:1");
        expected = -1;
        assertEquals(expected, actual);
        actual = Task1.minutesToSeconds("1:-1");
        expected = -1;
        assertEquals(expected, actual);
        actual = Task1.minutesToSeconds(":11");
        expected = -1;
        assertEquals(expected, actual);
        actual = Task1.minutesToSeconds("11:");
        expected = -1;
        assertEquals(expected, actual);
        actual = Task1.minutesToSeconds("aa:1");
        expected = -1;
        assertEquals(expected, actual);
        actual = Task1.minutesToSeconds("1:aa");
        expected = -1;
        assertEquals(expected, actual);
        actual = Task1.minutesToSeconds("1:60");
        expected = -1;
        assertEquals(expected, actual);
        actual = Task1.minutesToSeconds("0:0");
        expected = 0;
        assertEquals(expected, actual);
    }
}
