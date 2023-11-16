package edu.hw6.Task2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task2 {

    private Task2() {

    }

    public static boolean cloneFile(Path path) {
        if (path != null && !path.toString().isEmpty() && Files.exists(path)) {
            Path newPath = createPath(path);
            try {
                Files.copy(path, newPath);
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public static Path createPath(Path path) {
        Path newPath = null;
        int countFiles = 1;
        boolean isCreatedCopyFile = false;
        while (!isCreatedCopyFile) {
            newPath = Path.of(path.getParent() + "/" + createCopyName(path, countFiles));
            if (Files.notExists(newPath)) {
                isCreatedCopyFile = true;
            }
            countFiles++;
        }
        return newPath;
    }

    public static String createCopyName(Path path, int countFiles) {
        Path fileName = path.getFileName();
        String[] startAndEndFileName = fileName.toString().split("\\.");
        StringBuilder copyName = new StringBuilder();
        if (countFiles == 1) {
            copyName.append(startAndEndFileName[0]).append(" - копия").append(".").append(startAndEndFileName[1]);
        } else {
            copyName.append(startAndEndFileName[0]).append(" - копия (").append(countFiles).append(").")
                .append(startAndEndFileName[1]);
        }
        return copyName.toString();
    }
}
