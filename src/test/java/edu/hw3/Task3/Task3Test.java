package edu.hw3.Task3;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task3Test {

    @Test
    @DisplayName("Примеры из дз")
    void test1() {
        var actual = Task3.freqDict(new ArrayList<>(Arrays.asList("a", "bb", "a", "bb")));
        assertEquals("{bb=2, a=2}", actual.toString());

        actual = Task3.freqDict(new ArrayList<>(Arrays.asList("this", "and", "that", "and")));
        assertEquals("{that=1, and=2, this=1}", actual.toString());

        actual = Task3.freqDict(new ArrayList<>(Arrays.asList("this", "and", "that", "and")));
        assertEquals("{that=1, and=2, this=1}", actual.toString());

        actual = Task3.freqDict(new ArrayList<>(Arrays.asList(1, 1, 2, 2)));
        assertEquals("{1=2, 2=2}", actual.toString());
    }

    @Test
    @DisplayName("В freqDict передан пустой список")
    void test2() {
        var actual = Task3.freqDict(new ArrayList<>());
        assertEquals("{}", actual.toString());
    }

    @Test
    @DisplayName("В freqDict передан список пустых строк")
    void test3() {
        var actual = Task3.freqDict(new ArrayList<>(Arrays.asList("", "", "", "")));
        assertEquals("{=4}", actual.toString());
    }

    @Test
    @DisplayName("В freqDict передан список пробелов")
    void test4() {
        var actual = Task3.freqDict(new ArrayList<>(Arrays.asList(" ", " ", " ", " ")));
        assertEquals("{ =4}", actual.toString());
    }
}
