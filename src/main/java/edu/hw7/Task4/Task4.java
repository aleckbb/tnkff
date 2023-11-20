package edu.hw7.Task4;

import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings({"UncommentedMain", "RegexpSinglelineJava", "MagicNumber"})
public class Task4 {

    private Task4() {

    }

    // Cреднее время ускорения решения.
    // 1 поток: Согласно закону Амдала(доля последовательных вычислений равна 1) - 1
    //          На практике - 1, так как не отличается от однопоточного решения
    // 2 потока: Согласно закону Амдала(доля последовательных вычислений равна 0) - 2
    //          На практике - 2.42
    //          Показатель ускорения колебался от 1.72 до 3.75,
    //          максимальный показатель был при количестве точек: 100_000
    // 3 потока: Согласно закону Амдала(доля последовательных вычислений равна 0) - 3
    //          На практике - 3.49
    //          Показатель ускорения колебался от 2.36 до 4.84,
    //          максимальный показатель был при количестве точек: 100_000
    // 4 потока: Согласно закону Амдала(доля последовательных вычислений равна 0) - 4
    //          На практике - 4.01
    //          Показатель ускорения колебался от 2.96 до 6.05,
    //          максимальный показатель был при количестве точек: 100_000

    // Уровень погрешности для симуляции( Math.abs(multiThreadPI(numberOfIterations) - Math.PI) ):
    // в 10тыс симуляций - 0.016007346410206846
    // в 100тыc симуляций - 0.006767346410206709
    // в 1млн симуляций - 0.001179346410206783
    // в 10млн симуляций - 0.0005265464102

    public static void main(String[] args) {
        var first = System.nanoTime();
        System.out.printf("One Thread PI: %s%n", oneThreadPI(10000));
        var second = System.nanoTime();
        var duration = second - first;
        System.out.println(duration);
        first = System.nanoTime();
        System.out.printf("Multi Thread PI: %s%n", multiThreadPI(10000));
        second = System.nanoTime();
        var duration1 = second - first;
        System.out.println(duration1);
        System.out.println((double) duration / duration1);
        System.out.println(Math.abs(multiThreadPI(10000000) - Math.PI));
    }

    public static double oneThreadPI(int numberOfIterations) {
        int totalCount = 0;
        int circleCount = 0;
        Circle circle = new Circle();
        for (int i = 1; i <= numberOfIterations; ++i) {
            var point = new Point();
            if (circle.isPointOfCircle(point)) {
                circleCount++;
            }
            totalCount++;
        }
        return (double) (4 * circleCount) / totalCount;
    }

    public static double multiThreadPI(int numberOfIterations) {
        AtomicInteger totalCount = new AtomicInteger(0);
        AtomicInteger circleCount = new AtomicInteger(0);
        Circle circle = new Circle();
        int numberOfThread = 4;
        Runnable lambda = (() -> {
            for (int i = 1; i <= numberOfIterations / numberOfThread; ++i) {
                var point = new Point();
                if (circle.isPointOfCircle(point)) {
                    circleCount.incrementAndGet();
                }
                totalCount.incrementAndGet();
            }
        });
        var thread1 = new Thread(lambda);
        var thread2 = new Thread(lambda);
        var thread3 = new Thread(lambda);
        var thread4 = new Thread(lambda);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return (double) (4 * circleCount.get()) / totalCount.get();
    }
}
