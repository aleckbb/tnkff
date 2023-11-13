package edu.hw2.Task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    public final int intBOUND = 5;

    public Connection getConnection() {
        Random random = new Random();
        if (random.nextInt(intBOUND) == 0) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }
    }
}
