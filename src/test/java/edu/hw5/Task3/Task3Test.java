package edu.hw5.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class Task3Test {

    @Test
    @DisplayName("Программа верно обрабатывает время в стиле: 2023-11-13")
    void test1(){
        // Given
        String string = "2023-11-13";
        LocalDate expectedDate = LocalDate.of(2023, 11, 13);

        // When
        LocalDate actualDate = Task3.parseDate(string).orElse(null);

        // Then
        assertEquals(expectedDate, actualDate);
    }

    @Test
    @DisplayName("Программа верно обрабатывает время в стиле: 2023-1-3")
    void test2(){
        // Given
        String string = "2023-1-3";
        LocalDate expectedDate = LocalDate.of(2023, 1, 3);

        // When
        LocalDate actualDate = Task3.parseDate(string).orElse(null);

        // Then
        assertEquals(expectedDate, actualDate);
    }

    @Test
    @DisplayName("Программа верно обрабатывает время в стиле: 1/3/2023")
    void test3(){
        // Given
        String string = "1/3/2023";
        LocalDate expectedDate = LocalDate.of(2023, 1, 3);

        // When
        LocalDate actualDate = Task3.parseDate(string).orElse(null);

        // Then
        assertEquals(expectedDate, actualDate);
    }

    @Test
    @DisplayName("Программа верно обрабатывает время в стиле: 1/3/23")
    void test4(){
        // Given
        String string = "1/3/23";
        LocalDate expectedDate = LocalDate.of(2023, 1, 3);

        // When
        LocalDate actualDate = Task3.parseDate(string).orElse(null);

        // Then
        assertEquals(expectedDate, actualDate);
    }

    @Test
    @DisplayName("Программа верно обрабатывает время в стиле: tomorrow")
    void test5(){
        // Given
        String string = "tomorrow";
        LocalDate expectedDate = LocalDate.now().plusDays(1);

        // When
        LocalDate actualDate = Task3.parseDate(string).orElse(null);

        // Then
        assertEquals(expectedDate, actualDate);
    }

    @Test
    @DisplayName("Программа верно обрабатывает время в стиле: today")
    void test6(){
        // Given
        String string = "today";
        LocalDate expectedDate = LocalDate.now();

        // When
        LocalDate actualDate = Task3.parseDate(string).orElse(null);

        // Then
        assertEquals(expectedDate, actualDate);
    }

    @Test
    @DisplayName("Программа верно обрабатывает время в стиле: yesterday")
    void test7(){
        // Given
        String string = "yesterday";
        LocalDate expectedDate =  LocalDate.now().minusDays(1);

        // When
        LocalDate actualDate = Task3.parseDate(string).orElse(null);

        // Then
        assertEquals(expectedDate, actualDate);
    }

    @Test
    @DisplayName("Программа верно обрабатывает время в стиле: 1 day ago")
    void test8(){
        // Given
        String string = "1 day ago";
        LocalDate expectedDate = LocalDate.now().minusDays(1);

        // When
        LocalDate actualDate = Task3.parseDate(string).orElse(null);

        // Then
        assertEquals(expectedDate, actualDate);
    }

    @Test
    @DisplayName("Программа верно обрабатывает время в стиле: 2234 days ago")
    void test9(){
        // Given
        String string = "2234 days ago";
        LocalDate expectedDate = LocalDate.now().minusDays(2234);

        // When
        LocalDate actualDate = Task3.parseDate(string).orElse(null);

        // Then
        assertEquals(expectedDate, actualDate);
    }

    @Test
    @DisplayName("Поведение программы, если она получила формат времени, который не умеет обрабатывать")
    void test10(){
        // Given
        String string = "2023-11";

        // When
        LocalDate actualDate = Task3.parseDate(string).orElse(null);

        // Then
        assertNull(actualDate);
    }

    @Test
    @DisplayName("Поведение программы, если она получила пустую строку")
    void test11(){
        // Given
        String string = "";

        // When
        LocalDate actualDate = Task3.parseDate(string).orElse(null);

        // Then
        assertNull(actualDate);
    }

    @Test
    @DisplayName("Поведение программы, если она получила null")
    void test12(){
        // Given
        String string = null;

        // When
        LocalDate actualDate = Task3.parseDate(string).orElse(null);

        // Then
        assertNull(actualDate);
    }
}
