package edu.hw2.Task3;

public class StableConnection implements Connection {
    public void execute(String command) {
        Task3.LOGGER.info(command + Task3.COMPLETE);
    }

    public void close() {
        Task3.LOGGER.info(Task3.CONNECTION + this.getClass().getSimpleName() + Task3.CLOSE);
    }
}
