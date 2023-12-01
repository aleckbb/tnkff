package edu.hw5.Task6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task6Test {

    @Test
    @DisplayName("Проверка, что подпоследовательность есть")
    void test1() {
        // Given
        String str = "dadbdcd";
        String subsequence = "abc";

        // Then
        assertTrue(Task6.isSubsequence(subsequence, str));
    }

    @Test
    @DisplayName("Проверка, что подпоследовательности нет")
    void test2() {
        // Given
        String str = "dadbdd";
        String subsequence = "abc";

        // Then
        assertFalse(Task6.isSubsequence(subsequence, str));
    }

    @Test
    @DisplayName("Подпоследовательность короче строки, в которой происходит поиск")
    void test3() {
        // Given
        String str = "abcd";
        String subsequence = "abcdf";

        // Then
        assertFalse(Task6.isSubsequence(subsequence, str));
    }

    @Test
    @DisplayName("Cтрока, в которой происходит поиск, пустая")
    void test4() {
        // Given
        String str = "";
        String subsequence = "abcdf";

        // Then
        assertFalse(Task6.isSubsequence(subsequence, str));
    }

    @Test
    @DisplayName("Cтрока, в которой происходит поиск, равна null")
    void test5() {
        // Given
        String str = null;
        String subsequence = "abcdf";

        // Then
        assertFalse(Task6.isSubsequence(subsequence, str));
    }

    @Test
    @DisplayName("Подпоследовательность равна null")
    void test6() {
        // Given
        String str = "dadbdcd";
        String subsequence = null;

        // Then
        assertFalse(Task6.isSubsequence(subsequence, str));
    }

    @Test
    @DisplayName("Подпоследовательность пустая")
    void test7() {
        // Given
        String str = "dadbdcd";
        String subsequence = "";

        // Then
        assertFalse(Task6.isSubsequence(subsequence, str));
    }
}
