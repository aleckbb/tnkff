package edu.hw8.Task3;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@SuppressWarnings({"RegexpSinglelineJava", "MagicNumber", "UncommentedMain"})
public class FixedThreadSolve {
    private FixedThreadSolve() {

    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Path path = Path.of("src/main/java/edu/hw8/Task3/Database/database.txt");
        ReadDatabase.readInMap(path);
        ExecutorService threads = Executors.newFixedThreadPool(4);
        var start = System.nanoTime();
        var tasks = IntStream.range(1, 5)
            .mapToObj(passwordLength -> CompletableFuture.runAsync(() -> {
                PasswordGenerator passwordGenerator = new PasswordGenerator(passwordLength);
                String password = passwordGenerator.nextPassword();
                while (!Maps.passwordsFromDatabase.isEmpty() && !password.isEmpty()) {
                    String hash = HashGenerator.getMD5Hash(password);
                    if (Maps.passwordsFromDatabase.containsKey(hash)) {
                        Maps.passwordsOfClients.put(Maps.passwordsFromDatabase.remove(hash), password);
                    }
                    password = passwordGenerator.nextPassword();
                }
            }, threads))
            .limit(4)
            .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(tasks).join();
        var end = System.nanoTime() - start;
        threads.shutdown();
        System.out.println(end);
    }
}
