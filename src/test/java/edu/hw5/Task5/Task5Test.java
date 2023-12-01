package edu.hw5.Task5;

import java.util.regex.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task5Test {

    @Test
    @DisplayName("Правильный номер матчится")
    void test1() {
        // Given
        String str = "А123ВЕ777";

        // When
        Matcher matcher = Task5.RUSSIAN_NUMBER_PATTERN.matcher(str);

        // Then
        assertTrue(matcher.find());
    }

    @Test
    @DisplayName("Неправильный номер не матчится")
    void test2() {
        // Given
        String autoNumber = "А123ВЕ7777";

        // When
        Matcher matcher = Task5.RUSSIAN_NUMBER_PATTERN.matcher(autoNumber);

        // Then
        assertFalse(matcher.find());
    }
}
