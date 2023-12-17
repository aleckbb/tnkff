package edu.hw8.Task3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class ReadDatabase {

    private ReadDatabase() {

    }

    public static void readInMap(Path path) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            while ((line = br.readLine()) != null) {
                var userData = line.split(" ");
                Maps.passwordsFromDatabase.put(userData[1], userData[0]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
