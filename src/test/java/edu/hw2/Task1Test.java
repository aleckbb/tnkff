package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task1Test {

    @Test
    @DisplayName("Проверка Constant")
    void test1() {
        double actual = new Task1.Expr.Constant(-1).evaluate();
        double expected = -1;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Constant(0).evaluate();
        expected = 0;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Constant(1).evaluate();
        expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка Negate")
    void test2() {
        double actual = new Task1.Expr.Negate(new Task1.Expr.Constant(1)).evaluate();
        double expected = -1;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Negate(new Task1.Expr.Constant(0)).evaluate();
        expected = 0;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Negate(new Task1.Expr.Constant(-1)).evaluate();
        expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка Exponent")
    void test3() {
        double actual = new Task1.Expr.Exponent(new Task1.Expr.Constant(1), 10).evaluate();
        double expected = 1;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Exponent(new Task1.Expr.Constant(0), 10).evaluate();
        expected = 0;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Exponent(new Task1.Expr.Constant(-1), 10).evaluate();
        expected = 1;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Exponent(new Task1.Expr.Constant(-1), 9).evaluate();
        expected = -1;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка Addition")
    void test4() {
        double actual = new Task1.Expr.Addition(new Task1.Expr.Constant(1), new Task1.Expr.Constant(1)).evaluate();
        double expected = 2;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Addition(new Task1.Expr.Constant(0), new Task1.Expr.Constant(1)).evaluate();
        expected = 1;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Addition(new Task1.Expr.Constant(-1), new Task1.Expr.Constant(1)).evaluate();
        expected = 0;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Addition(new Task1.Expr.Constant(0), new Task1.Expr.Constant(0)).evaluate();
        expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка Multiplication")
    void test5() {
        double actual =
            new Task1.Expr.Multiplication(new Task1.Expr.Constant(1), new Task1.Expr.Constant(1)).evaluate();
        double expected = 1;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Multiplication(new Task1.Expr.Constant(0), new Task1.Expr.Constant(1)).evaluate();
        expected = 0;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Multiplication(new Task1.Expr.Constant(-1), new Task1.Expr.Constant(1)).evaluate();
        expected = -1;
        assertEquals(expected, actual);

        actual = new Task1.Expr.Multiplication(new Task1.Expr.Constant(0), new Task1.Expr.Constant(0)).evaluate();
        expected = 0;
        assertEquals(expected, actual);
    }
}
