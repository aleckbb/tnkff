package edu.hw3.Task7;

import java.util.TreeMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task7Test {

    @Test
    @DisplayName("Пример из дз")
    void test1() {
        TreeMap<String, String> tree = new TreeMap<>(new MyComparator());
        tree.put(null, "test");
        assertTrue(tree.containsKey(null));
    }

    @Test
    @DisplayName("null такой же ключ")
    void test2() {
        TreeMap<String, String> tree = new TreeMap<>(new MyComparator());
        tree.put(null, "test");
        assertEquals("test", tree.get(null));
    }

    @Test
    @DisplayName("null не существует по умолчанию")
    void test3() {
        TreeMap<String, String> tree = new TreeMap<>(new MyComparator());
        tree.put("key", "test");
        assertFalse(tree.containsKey(null));
    }
}
