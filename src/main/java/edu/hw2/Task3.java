package edu.hw2;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task3 {

    private final static Logger LOGGER = LogManager.getLogger();

    private Task3() {
    }

    public static void main(String[] args) throws Exception {
        PopularCommandExecutor executor;
        executor = new PopularCommandExecutor(1);
        executor.updatePackages();
    }

    public interface Connection extends AutoCloseable {
        void execute(String command);
    }

    public static class StableConnection implements Connection {
        public void execute(String command) {
            LOGGER.info(command + " выполнена!");
        }

        public void close() {
            LOGGER.info("Соединение для " + this.getClass().getSimpleName() + " закрыто");
        }
    }

    public static class FaultyConnection implements Connection {
        public final int BOUND = 2;

        public void execute(String command) throws ConnectionException {
            Random random = new Random();

            if (random.nextInt(BOUND) == 1) {
                throw new ConnectionException("Error", new RuntimeException());
            }
            LOGGER.info(command + " выполнена!");
        }

        public void close() {
            LOGGER.info("Соединение для " + this.getClass().getSimpleName() + " закрыто");
        }
    }

    public interface ConnectionManager {
        Connection getConnection();
    }

    public static class DefaultConnectionManager implements ConnectionManager {
        public final int BOUND = 5;

        public Connection getConnection() {
            Random random = new Random();
            if (random.nextInt(BOUND) == 0) {
                return new FaultyConnection();
            } else {
                return new StableConnection();
            }
        }
    }

    public static class FaultyConnectionManager implements ConnectionManager {
        public Connection getConnection() {
            return new FaultyConnection();
        }
    }

    public static class ConnectionException extends RuntimeException {
        public ConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static final class PopularCommandExecutor {
        private final ConnectionManager manager;
        private final int maxAttempts;

        PopularCommandExecutor(int maxAttempts) {
            int BOUND = 2;
            Random random = new Random();
            manager = (random.nextInt(BOUND) == 1) ? new DefaultConnectionManager() : new FaultyConnectionManager();
            this.maxAttempts = maxAttempts;
        }

        public void updatePackages() throws Exception {
            tryExecute("apt update && apt upgrade -y");
        }

        void tryExecute(String command) throws Exception {
            final Connection connection = manager.getConnection();
            boolean fl = true;
            if (maxAttempts == 0) {
                throw new ConnectionException("Error", new RuntimeException());
            }
            for (int cnt = 1; cnt <= maxAttempts && fl; cnt++) {
                try {
                    connection.execute(command);
                    fl = false;
                } catch (ConnectionException e) {
                    fl = true;
                    if (cnt == maxAttempts) {
                        throw new ConnectionException("Error", new RuntimeException());
                    }
                }
            }
            connection.close();
        }
    }
}
