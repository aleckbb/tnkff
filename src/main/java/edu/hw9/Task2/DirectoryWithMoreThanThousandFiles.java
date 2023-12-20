package edu.hw9.Task2;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.RecursiveTask;

public class DirectoryWithMoreThanThousandFiles extends RecursiveTask<List<Path>> {

    private static final int THOUSAND = 1000;
    private final Path directoryPath;
    private final List<Path> answer;

    DirectoryWithMoreThanThousandFiles(Path directoryPath, List<Path> answer) {
        this.directoryPath = directoryPath;
        this.answer = answer;
    }

    @Override
    protected List<Path> compute() {
        for (var currentFile : Objects.requireNonNull(directoryPath.toFile().listFiles())) {
            if (currentFile.isDirectory()) {
                if (Objects.requireNonNull(currentFile.listFiles()).length >= THOUSAND) {
                    answer.add(Path.of(currentFile.getAbsolutePath()));
                }
                DirectoryWithMoreThanThousandFiles directoryWithMoreThanThousandFiles =
                    new DirectoryWithMoreThanThousandFiles(Path.of(currentFile.getAbsolutePath()), answer);
                directoryWithMoreThanThousandFiles.fork();
                directoryWithMoreThanThousandFiles.join();
            }
        }
        return answer;
    }
}
