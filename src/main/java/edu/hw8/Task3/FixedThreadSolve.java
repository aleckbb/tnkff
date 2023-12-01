package edu.hw8.Task3;

import java.nio.file.Path;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SuppressWarnings({"RegexpSinglelineJava", "MagicNumber", "UncommentedMain"})
public class FixedThreadSolve {
    private FixedThreadSolve() {

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        PasswordGenerator passwordGenerator = new PasswordGenerator(4);
        Path path = Path.of("src/main/java/edu/hw8/Task3/Database/database.txt");
        ReadDatabase.readInMap(path);
        ExecutorService threads = Executors.newFixedThreadPool(6);
        Future<?> future = threads.submit(() -> {
            String password = passwordGenerator.nextPassword();
            while (Maps.passwordsFromDatabase.size() > 0) {
                String hash = HashGenerator.getMD5Hash(password);
                if (Maps.passwordsFromDatabase.containsKey(hash)) {
                    Maps.passwordsOfClients.put(Maps.passwordsFromDatabase.remove(hash), password);
                }
                password = passwordGenerator.nextPassword();
            }
        });
        future.get();
        threads.shutdown();
        System.out.println(Maps.passwordsOfClients.toString());
    }
}
