package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task2Test {

    @Test
    @DisplayName("Имя копии создается правильно, когда у файла нет копий")
    void test1(){
        // Given
        Path path = Path.of("src/main/java/edu/hw6/Task2/Bururi.txt");
        String expected = "src/main/java/edu/hw6/Task2/Bururi - копия.txt";

        // Then
        assertEquals(Path.of(expected), Task2.createPath(path));
    }

    @Test
    @DisplayName("Имя копии создается правильно, когда у файла есть копии")
    void test2(){
        // Given
        Path path = Path.of("src/main/java/edu/hw6/Task2/Tinkoff Bank Biggest Secret.txt");
        String expected = "src/main/java/edu/hw6/Task2/Tinkoff Bank Biggest Secret - копия (2).txt";

        // Then
        assertEquals(Path.of(expected), Task2.createPath(path));
    }

    @Test
    @DisplayName("Копия создается")
    void test3(){
        // Given
        Path path = Path.of("src/main/java/edu/hw6/Task2/Bururi.txt");
        Path newPath = Task2.createPath(path);

        // When
        Task2.cloneFile(path);

        // Then
        assertTrue(Files.exists(newPath));
        try {
            Files.delete(newPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Если путь до файла пустая строка, то ничего не произойдет")
    void test4(){
        // Given
        Path path = Path.of("");

        // Then
        assertFalse(Task2.cloneFile(path));
    }

    @Test
    @DisplayName("Если путь до файла равен null, то ничего не произойдет")
    void test5(){
        // Given
        Path path = null;

        // Then
        assertFalse(Task2.cloneFile(path));
    }
}
