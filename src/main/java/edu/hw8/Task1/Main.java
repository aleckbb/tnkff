package edu.hw8.Task1;

import java.io.IOException;

@SuppressWarnings("MagicNumber")
public class Main {
    private Main() {

    }

    public static void main(String[] args) {
        Server server = new Server(4);

        try {
            server.start(8080);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            server.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
