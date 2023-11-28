package edu.hw8.Task1;

import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {

    @Test
    @DisplayName("Penis")
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

        Client client = new Client();
        Thread.sleep(1000);
        client.start("localhost", 18080);

        client.sendToServer("оскорбления");
        client.readFromServer();

        Thread.sleep(1000);
        try {
            server.close();
            client.close();
            thread.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
