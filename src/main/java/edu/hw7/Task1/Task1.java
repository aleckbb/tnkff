package edu.hw7.Task1;

import java.util.concurrent.atomic.AtomicInteger;

public class Task1 {

    private Task1() {

    }

    public static int parallelIncrement() {
        AtomicInteger integer = new AtomicInteger(0);
        var thread1 = new Thread(integer::incrementAndGet);
        var thread2 = new Thread(integer::incrementAndGet);
        var thread3 = new Thread(integer::incrementAndGet);
        var thread4 = new Thread(integer::incrementAndGet);
        var thread5 = new Thread(integer::incrementAndGet);
        var thread6 = new Thread(integer::incrementAndGet);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return integer.get();
    }
}
