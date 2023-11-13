package edu.hw5.Task8;

import java.util.regex.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task8Test {
    @Test
    @DisplayName("Первое регулярное выражение для строки, которая заматчится")
    void test1() {
        // Given
        String str = "010";

        // When
        Matcher matcher = Task8.FIRST_PATTERN.matcher(str);

        // Then
        assertTrue(matcher.find());
    }

    @Test
    @DisplayName("Первое регулярное выражение для строки, которая не  заматчится")
    void test2() {
        // Given
        String str = "0111";

        // When
        Matcher matcher = Task8.FIRST_PATTERN.matcher(str);

        // Then
        assertFalse(matcher.find());
    }

    @Test
    @DisplayName("Второе регулярное выражение для строки, которая начинается с 0 и нечетной длины")
    void test3() {
        // Given
        String str = "01101";

        // When
        Matcher matcher = Task8.SECOND_PATTERN.matcher(str);

        // Then
        assertTrue(matcher.find());
    }

    @Test
    @DisplayName("Второе регулярное выражение для строки, которая начинается с 1 и четной длины")
    void test4() {
        // Given
        String str = "1101";

        // When
        Matcher matcher = Task8.SECOND_PATTERN.matcher(str);

        // Then
        assertTrue(matcher.find());
    }

    @Test
    @DisplayName("Второе регулярное выражение для строки, которая не заматчится")
    void test5() {
        // Given
        String str = "011111";

        // When
        Matcher matcher = Task8.SECOND_PATTERN.matcher(str);

        // Then
        assertFalse(matcher.find());
    }

    @Test
    @DisplayName("Третье регулярное выражение для строки, которая заматчится")
    void test6() {
        // Given
        String str = "010";

        // When
        Matcher matcher = Task8.THIRD_PATTERN.matcher(str);

        // Then
        assertTrue(matcher.find());
    }

    @Test
    @DisplayName("Третье регулярное выражение для строки, которая не  заматчится")
    void test7() {
        // Given
        String str = "011111";

        // When
        Matcher matcher = Task8.THIRD_PATTERN.matcher(str);

        // Then
        assertFalse(matcher.find());
    }

    @Test
    @DisplayName("Четвертое регулярное выражение для строки, которая заматчится")
    void test8() {
        // Given
        String str = "1010";

        // When
        Matcher matcher = Task8.FOURS_PATTERN.matcher(str);

        // Then
        assertTrue(matcher.find());
    }

    @Test
    @DisplayName("Четвертое регулярное выражение для строки, которая не  заматчится")
    void test9() {
        // Given
        String str = "0111110";

        // When
        Matcher matcher = Task8.FOURS_PATTERN.matcher(str);

        // Then
        assertFalse(matcher.find());
    }

}
