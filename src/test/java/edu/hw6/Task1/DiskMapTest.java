package edu.hw6.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DiskMapTest {

    @Test
    @DisplayName("Проверка, что элемент добавился")
    void test1(){
        // Given
        DiskMap diskMap = new DiskMap();

        // When
        diskMap.put("1", "1");

        // Then
        assertEquals("{1=1}", diskMap.toString());
    }

    @Test
    @DisplayName("Проверка, что элемент перезаписался")
    void test2(){
        // Given
        DiskMap diskMap = new DiskMap();

        // When
        diskMap.put("1", "2");
        diskMap.put("1", "1");

        // Then
        assertEquals("{1=1}", diskMap.toString());
    }

    @Test
    @DisplayName("Проверка, что элемент удалился")
    void test3(){
        // Given
        DiskMap diskMap = new DiskMap();

        // When
        diskMap.put("2", "2");
        diskMap.put("1", "1");
        diskMap.remove("2");

        // Then
        assertEquals("{1=1}", diskMap.toString());
    }

    @Test
    @DisplayName("Удаление из пустой мапы")
    void test4(){
        // Given
        DiskMap diskMap = new DiskMap();

        // Then
        assertNull(diskMap.remove("1"));
    }
}
