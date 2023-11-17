package edu.hw3.Task2;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task2Test {

    @Test
    @DisplayName("Примеры из дз")
    void test1() {
        assertEquals(List.of("()", "()", "()"), List.of(Task2.clusterize("()()()")));

        assertEquals(List.of("((()))"), List.of(Task2.clusterize("((()))")));

        assertEquals(
            List.of("((()))", "(())", "()", "()", "(()())"),
            List.of(Task2.clusterize("((()))(())()()(()())"))
        );

        assertEquals(List.of("((())())", "(()(()()))"), List.of(Task2.clusterize("((())())(()(()()))")));
    }

    @Test
    @DisplayName("Пустая строка")
    void test2() {
        assertEquals(List.of(), List.of(Task2.clusterize("")));
    }

    @Test
    @DisplayName("Строка представлена пробелом")
    void test3() {
        assertEquals(List.of(), List.of(Task2.clusterize(" ")));
    }

    @Test
    @DisplayName("Открывающих скобок больше чем закрывающих")
    void test4() {
        assertEquals(List.of(), List.of(Task2.clusterize("(()")));

        assertEquals(List.of(), List.of(Task2.clusterize("()(")));

    }

    @Test
    @DisplayName("Закрывающих скобок больше чем открывающих")
    void test5() {
        assertEquals(List.of(), List.of(Task2.clusterize(")()")));

        assertEquals(List.of(), List.of(Task2.clusterize("())")));
    }

    @Test
    @DisplayName("В строке другие символы")
    void test6() {
        assertEquals(List.of(), List.of(Task2.clusterize("а")));

        assertEquals(List.of("()"), List.of(Task2.clusterize("а()")));

        assertEquals(List.of("()"), List.of(Task2.clusterize("(a)")));

        assertEquals(List.of("()"), List.of(Task2.clusterize("()a")));

        assertEquals(List.of("()"), List.of(Task2.clusterize("a(a)a")));
    }

    @Test
    @DisplayName("В строке другие скобки")
    void test7() {
        assertEquals(List.of("()"), List.of(Task2.clusterize("()[]{}")));

        assertEquals(List.of("()"), List.of(Task2.clusterize("([)]")));

        assertEquals(List.of(), List.of(Task2.clusterize("[]")));
    }
}
