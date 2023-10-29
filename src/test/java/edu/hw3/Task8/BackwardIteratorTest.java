package edu.hw3.Task8;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BackwardIteratorTest {

    @Test
    @DisplayName("Пример из дз")
    void test1() {
        List<?> list = List.of(1, 2, 3);
        BackwardIterator<?> backwardIterator = new BackwardIterator<>(list);
        int i = list.size() - 1;
        boolean fl = true;
        while (backwardIterator.hasNext()) {
            if (list.get(i) != backwardIterator.next()) {
                fl = false;
            }
            i--;
        }
        assertTrue(fl);
    }

    @Test
    @DisplayName("Проверка hasNext с коллекцией нулевой длины")
    void test2() {
        BackwardIterator<?> backwardIterator = new BackwardIterator<>(List.of());
        assertFalse(backwardIterator.hasNext());
    }

    @Test
    @DisplayName("null как коллекция")
    void test3() {
        assertThrows(NullPointerException.class, () -> new BackwardIterator<>(null));
    }

    @Test
    @DisplayName("Коллекция типа string")
    void test4() {
        List<?> list = List.of("1", "2", "3");
        BackwardIterator<?> backwardIterator = new BackwardIterator<>(list);
        int i = list.size() - 1;
        boolean fl = true;
        while (backwardIterator.hasNext()) {
            if (!list.get(i).equals(backwardIterator.next())) {
                fl = false;
            }
            i--;
        }
        assertTrue(fl);
    }

    @Test
    @DisplayName("Коллекция типа Person(пользовательский класс)")
    void test5() {
        List<?> list = List.of(new Person("Дима"), new Person("Саша"), new Person("Ваня"));
        BackwardIterator<?> backwardIterator = new BackwardIterator<>(list);
        int i = list.size() - 1;
        boolean fl = true;
        while (backwardIterator.hasNext()) {
            if (!list.get(i).equals(backwardIterator.next())) {
                fl = false;
            }
            i--;
        }
        assertTrue(fl);
    }
}
