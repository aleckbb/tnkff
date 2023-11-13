package edu.hw2.Task3;

import java.util.Random;

public class FaultyConnection implements Connection {
    public final int intBOUND = 2;

    public void execute(String command) throws ConnectionException {
        Random random = new Random();

        if (random.nextInt(intBOUND) == 0) {
            throw new ConnectionException(Task3.ERROR, new RuntimeException());
        }
        Task3.LOGGER.info(command + Task3.COMPLETE);
    }

    public void close() {
        Task3.LOGGER.info(Task3.CONNECTION + this.getClass().getSimpleName() + Task3.CLOSE);
    }
}
