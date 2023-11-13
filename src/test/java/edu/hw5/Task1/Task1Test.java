package edu.hw5.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task1Test {

    @Test
    @DisplayName("Метод нахождения среднего значения времени работает кооректно")
    void test1() {
        // Given
        String sessions = """
            2022-03-12, 20:20 - 2022-03-12, 23:50
            2022-04-01, 21:30 - 2022-04-02, 01:20
            """;

        // When
        String expected = "3:40";

        // Then
        assertEquals(expected, Task1.averageSessionDuration(sessions));
    }

    @Test
    @DisplayName("Передавая строка равна null")
    void test2() {
        // Given
        String sessions = null;

        // When
        String expected = "Данные введены некорректно";

        // Then
        assertEquals(expected, Task1.averageSessionDuration(sessions));
    }

    @Test
    @DisplayName("Передавая строка равна пустая")
    void test3() {
        // Given
        String sessions = "";

        // When
        String expected = "Данные введены некорректно";

        // Then
        assertEquals(expected, Task1.averageSessionDuration(sessions));
    }

    @Test
    @DisplayName("Передавая строка неверного формата")
    void test4() {
        // Given
        String sessions = """
            2022-03, 20:20 - 2022-03, 23:50
            21:30 - 01:20
            """;

        // When
        String expected = "Данные введены некорректно";

        // Then
        assertEquals(expected, Task1.averageSessionDuration(sessions));
    }

    @Test
    @DisplayName("Передавая строка неверного формата")
    void test5() {
        // Given
        String sessions = """
            2022-03, 20:20 - 2022-03, 23:50
            21:30 - 01:20
            """;

        // Then
        assertFalse(Task1.isValidSessions(sessions));
    }

    @Test
    @DisplayName("Передавая строка верного формата")
    void test6() {
        // Given
        String sessions = """
            2022-03-12, 20:20 - 2022-03-12, 23:50
            2022-04-01, 21:30 - 2022-04-02, 01:20
            """;

        // Then
        assertTrue(Task1.isValidSessions(sessions));
    }

    @Test
    @DisplayName("Передавая строка некорректная")
    void test7() {
        // Given
        String sessions = """
            2022-13-12, 24:20 - 2022-03-12, 23:50
            2022-04-55, 21:30 - 2022-04-02, 01:20
            """;

        // When
        String expected = "Данные введены некорректно";

        // Then
        assertEquals(expected, Task1.averageSessionDuration(sessions));
    }

    @Test
    @DisplayName("Передавая строка некорректная")
    void test8() {
        // Given
        String sessions = """
            2022-13-12, 24:20 - 2022-03-12, 23:50
            2022-04-55, 21:30 - 2022-04-02, 01:20
            """;

        // Then
        assertFalse(Task1.isCorrectSessions(sessions));
    }

    @Test
    @DisplayName("Передавая строка корректная")
    void test9() {
        // Given
        String sessions = """
            2022-03-12, 20:20 - 2022-03-12, 23:50
            2022-04-01, 21:30 - 2022-04-02, 01:20
            """;

        // Then
        assertTrue(Task1.isCorrectSessions(sessions));
    }
}
