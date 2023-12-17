package edu.hw8.Task3;

import java.nio.file.Path;

@SuppressWarnings({"UncommentedMain", "MagicNumber", "RegexpSinglelineJava"})
public class SingleThreadSolve {
    private SingleThreadSolve() {

    }

    public static void main(String[] args) {
        Path path = Path.of("src/main/java/edu/hw8/Task3/Database/database.txt");
        ReadDatabase.readInMap(path);
        var first = System.nanoTime();
        for (int i = 1; i < 5; i++) {
            PasswordGenerator passwordGenerator = new PasswordGenerator(i);
            String password = passwordGenerator.nextPassword();
            while (!Maps.passwordsFromDatabase.isEmpty() && !password.isEmpty()) {
                String hash = HashGenerator.getMD5Hash(password);
                if (Maps.passwordsFromDatabase.containsKey(hash)) {
                    Maps.passwordsOfClients.put(Maps.passwordsFromDatabase.remove(hash), password);
                }
                password = passwordGenerator.nextPassword();
            }
        }
        System.out.println(System.nanoTime() - first);
    }
}
