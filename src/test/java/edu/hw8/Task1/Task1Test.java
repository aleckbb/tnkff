package edu.hw8.Task1;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {

    private List<String> list = List.of("личности", "оскорбления", "глупый", "интеллект");

    @Test
    @DisplayName("Сервер и клиенты общаются")
    void test1() throws InterruptedException {

        Server server = new Server(4);
        Thread thread = new Thread(() -> {
            try {
                server.start(18080);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread.start();

        Thread.sleep(1000);

        ExecutorService clients = Executors.newFixedThreadPool(5);
        var futures = Stream.generate(() -> CompletableFuture.runAsync(() -> {
                Client client = new Client();
                client.start("localhost", 18080);
                client.sendToServer(list.get(ThreadLocalRandom.current().nextInt(4)));
                client.readFromServer();
                try {
                    client.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }, clients))
            .limit(5)
            .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        clients.shutdown();

        Thread.sleep(1000);
        try {
            server.close();
            thread.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
