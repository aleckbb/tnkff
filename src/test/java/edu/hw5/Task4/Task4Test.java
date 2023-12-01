package edu.hw5.Task4;

import java.util.regex.Matcher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task4Test {
    @Test
    @DisplayName("Правильный пароль матчится")
    void test1() {
        // Given
        String str = "12345!";

        // When
        Matcher matcher = Task4.SPECIAL_SYMBOL_PATTERN.matcher(str);

        // Then
        assertTrue(matcher.find());
    }

    @Test
    @DisplayName("Неправильный пароль не матчится")
    void test2() {
        // Given
        String autoNumber = "12345";

        // When
        Matcher matcher = Task4.SPECIAL_SYMBOL_PATTERN.matcher(autoNumber);

        // Then
        assertFalse(matcher.find());
    }

}
