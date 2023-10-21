package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task4Test {

    @Test
    @DisplayName("Вызов из метода test1")
    void test1() {
        Task4.CallingInfo actual = Task4.callingInfo();
        Task4.CallingInfo expected = new Task4.CallingInfo("edu.hw2.Task4Test", "test1");
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Вызов из метода exampleMethod класса Example")
    void test2() {
        class Example {
            Example() {

            }

            public Task4.CallingInfo exampleMethod() {
                return Task4.callingInfo();
            }
        }
        Example example = new Example();
        Task4.CallingInfo actual = example.exampleMethod();
        Task4.CallingInfo expected = new Task4.CallingInfo("edu.hw2.Task4Test$1Example", "exampleMethod");
        assertEquals(expected, actual);
    }
}
