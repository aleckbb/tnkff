package edu.hw2.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task4Test {

    @Test
    @DisplayName("Вызов из метода test1")
    void test1() {
        CallingInfo actual = Task4.callingInfo();
        CallingInfo expected = new CallingInfo("edu.hw2.Task4.Task4Test", "test1");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Вызов из метода exampleMethod класса Example")
    void test2() {
        // Given
        class Example {
            Example() {

            }

            public CallingInfo exampleMethod() {
                return Task4.callingInfo();
            }
        }
        Example example = new Example();
        // When
        CallingInfo actual = example.exampleMethod();
        CallingInfo expected = new CallingInfo("edu.hw2.Task4.Task4Test$1Example", "exampleMethod");
        // Then
        assertEquals(expected, actual);
    }
}
