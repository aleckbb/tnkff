package edu.hw7.Task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;

class Task1Test {
    AtomicInteger counter = new AtomicInteger(0);
    @BeforeEach
    void openThread(){
        counter.set(0);
        var thread1 = new Thread(counter::incrementAndGet);
        var thread2 = new Thread(counter::incrementAndGet);
        var thread3 = new Thread(counter::incrementAndGet);
        thread1.start();
        thread2.start();
        thread3.start();
        try{
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @RepeatedTest(10)
    @DisplayName("Счетчик потокобезопасен и использует класс AtomicInteger")
    void test(){
        assertEquals(3, counter.get());
    }
}
