package edu.hw8.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class Task1Test {

    @Test
    @DisplayName(" ")
    void test1() throws IOException, InterruptedException {
        Server server = new Server(4);

        Thread thread = new Thread(() -> {
            try {
                server.start(4004);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        Client client = new Client();
        client.start("127.0.0.1", 4004);

        client.sendToServer("личности");
        client.readFromServer();

        try {
            server.close();
            client.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
