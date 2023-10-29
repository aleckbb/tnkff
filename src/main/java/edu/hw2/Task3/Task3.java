package edu.hw2.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task3 {

    protected static final String ERROR = "Error";

    protected static final String COMPLETE = " выполнена!";

    protected static final String CONNECTION = "Соединение для ";

    protected static final String CLOSE = " закрыто";

    protected final static Logger LOGGER = LogManager.getLogger();

    private Task3() {
    }

    public static void main(String[] args) throws Exception {
        PopularCommandExecutor executor = new PopularCommandExecutor(1, false);
        executor.updatePackages();
    }

    public static final class PopularCommandExecutor {
        private final ConnectionManager manager;
        private final int maxAttempts;

        PopularCommandExecutor(int maxAttempts, boolean fl) {
            manager = fl ? new DefaultConnectionManager() : new FaultyConnectionManager();
            this.maxAttempts = maxAttempts;
        }

        public void updatePackages() throws Exception {
            tryExecute("apt update && apt upgrade -y");
        }

        void tryExecute(String command) throws Exception {
            final Connection connection = manager.getConnection();
            boolean fl = true;
            if (maxAttempts == 0) {
                throw new ConnectionException(ERROR, new RuntimeException());
            }
            for (int cnt = 1; cnt <= maxAttempts && fl; cnt++) {
                try (connection) {
                    connection.execute(command);
                    fl = false;
                } catch (ConnectionException e) {
                    fl = true;
                    if (cnt == maxAttempts) {
                        throw new ConnectionException(ERROR, e.getCause());
                    }
                }
            }
        }
    }
}
