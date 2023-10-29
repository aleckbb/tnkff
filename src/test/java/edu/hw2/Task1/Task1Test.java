package edu.hw2.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task1Test {

    @Test
    @DisplayName("Проверка Constant")
    void test1() {
        // When
        double actual = new Expr.Constant(-1).evaluate();
        double expected = -1;
        // Then
        assertEquals(expected, actual);

        // When
        actual = new Expr.Constant(0).evaluate();
        expected = 0;
        // Then
        assertEquals(expected, actual);

        // When
        actual = new Expr.Constant(1).evaluate();
        expected = 1;
        // Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка Negate")
    void test2() {
        // When
        double actual = new Expr.Negate(new Expr.Constant(1)).evaluate();
        double expected = -1;
        // Then
        assertEquals(expected, actual);

        // When
        actual = new Expr.Negate(new Expr.Constant(0)).evaluate();
        expected = 0;
        // Then
        assertEquals(expected, actual);

        // When
        actual = new Expr.Negate(new Expr.Constant(-1)).evaluate();
        expected = 1;
        // Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка Exponent")
    void test3() {
        // When
        double actual = new Expr.Exponent(new Expr.Constant(1), 10).evaluate();
        double expected = 1;
        // Then
        assertEquals(expected, actual);

        // When
        actual = new Expr.Exponent(new Expr.Constant(0), 10).evaluate();
        expected = 0;
        // Then
        assertEquals(expected, actual);

        // When
        actual = new Expr.Exponent(new Expr.Constant(-1), 10).evaluate();
        expected = 1;
        // Then
        assertEquals(expected, actual);

        // When
        actual = new Expr.Exponent(new Expr.Constant(-1), 9).evaluate();
        expected = -1;
        // Then
        assertEquals(expected, actual);

        // When
        actual = new Expr.Exponent(new Expr.Constant(0), -1).evaluate();
        expected = Double.POSITIVE_INFINITY;
        // Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка Addition")
    void test4() {
        // When
        double actual = new Expr.Addition(new Expr.Constant(1), new Expr.Constant(1)).evaluate();
        double expected = 2;
        // Then
        assertEquals(expected, actual);

        // When
        actual = new Expr.Addition(new Expr.Constant(0), new Expr.Constant(1)).evaluate();
        expected = 1;
        // Then
        assertEquals(expected, actual);

        // When
        actual = new Expr.Addition(new Expr.Constant(-1), new Expr.Constant(1)).evaluate();
        expected = 0;
        // Then
        assertEquals(expected, actual);

        // When
        actual = new Expr.Addition(new Expr.Constant(0), new Expr.Constant(0)).evaluate();
        expected = 0;
        // Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Проверка Multiplication")
    void test5() {
        // When
        double actual =
            new Expr.Multiplication(new Expr.Constant(1), new Expr.Constant(1)).evaluate();
        double expected = 1;
        // Then
        assertEquals(expected, actual);

        // When
        actual = new Expr.Multiplication(new Expr.Constant(0), new Expr.Constant(1)).evaluate();
        expected = 0;
        // Then
        assertEquals(expected, actual);

        // When
        actual = new Expr.Multiplication(new Expr.Constant(-1), new Expr.Constant(1)).evaluate();
        expected = -1;
        // Then
        assertEquals(expected, actual);

        // When
        actual = new Expr.Multiplication(new Expr.Constant(0), new Expr.Constant(0)).evaluate();
        expected = 0;
        // Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Пример из дз")
    void test6() {
        // Given
        var two = new Expr.Constant(2);
        var four = new Expr.Constant(4);
        var negOne = new Expr.Negate(new Expr.Constant(1));
        var sumTwoFour = new Expr.Addition(two, four);
        var mult = new Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Expr.Exponent(mult, 2);
        var res = new Expr.Addition(exp, new Expr.Constant(1));
        // When
        double actual = res.evaluate();
        double expected = 37.0;
        // Then
        assertEquals(expected, actual);
    }
}
