package edu.hw7.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task1Test {
    @RepeatedTest(10)
    @DisplayName("Счетчик потокобезопасен и использует класс AtomicInteger")
    void test(){
        assertEquals(6, Task1.parallelIncrement());
    }
}
