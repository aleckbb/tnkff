package edu.hw9.Task1;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {

    private List<String> metricNames = List.of(
        "a", "b", "c", "d", "e", "f", "g", "h", "i", "j"
    );

    @Test
    @DisplayName("Программа справляется с задачей")
    void test1() {
        int collectorSize = 4;
        int countOfMetrics = 10;
        StatsCollector collector = new StatsCollector(collectorSize);

        var send = Stream.generate(() -> CompletableFuture.runAsync(() ->
                collector.push(
                    metricNames.get(ThreadLocalRandom.current().nextInt(10)),
                    new double[] {ThreadLocalRandom.current().nextDouble(-5, 5),
                        ThreadLocalRandom.current().nextDouble(-5, 5),
                        ThreadLocalRandom.current().nextDouble(-5, 5),
                        ThreadLocalRandom.current().nextDouble(-5, 5)}
                ), Executors.newFixedThreadPool(collectorSize)))
            .limit(countOfMetrics)
            .toArray(CompletableFuture[]::new);

        System.out.printf("name        summ                avg                max                min\n");
        var answer = Stream.generate(() -> CompletableFuture.runAsync(
                () ->
                    System.out.println(collector.collectStats()),
                Executors.newFixedThreadPool(collectorSize)
            ))
            .limit(countOfMetrics)
            .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(send).join();
        CompletableFuture.allOf(answer).join();
    }
}
