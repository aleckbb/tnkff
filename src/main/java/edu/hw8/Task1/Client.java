package edu.hw8.Task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements AutoCloseable {

    private Socket client;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public Client() {

    }

    public void start(String host, int port) {
        try {
            client = new Socket(InetAddress.getByName(host), port);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendToServer(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFromServer() {
        try {
            var response = bufferedReader.readLine();
            System.out.println("Answer: " + response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        client.close();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
