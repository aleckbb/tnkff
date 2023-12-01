package edu.hw8.Task3;

import java.nio.file.Path;

@SuppressWarnings({"UncommentedMain", "MagicNumber", "RegexpSinglelineJava"})
public class SingleThreadSolve {
    private SingleThreadSolve() {

    }

    public static void main(String[] args) {
        PasswordGenerator passwordGenerator = new PasswordGenerator(4);
        Path path = Path.of("src/main/java/edu/hw8/Task3/Database/database.txt");
        ReadDatabase.readInMap(path);
        String password = passwordGenerator.nextPassword();
        while (Maps.passwordsFromDatabase.size() > 0) {
            String hash = HashGenerator.getMD5Hash(password);
            if (Maps.passwordsFromDatabase.containsKey(hash)) {
                Maps.passwordsOfClients.put(Maps.passwordsFromDatabase.remove(hash), password);
            }
            password = passwordGenerator.nextPassword();
        }
        System.out.println(Maps.passwordsOfClients.toString());
    }
}
