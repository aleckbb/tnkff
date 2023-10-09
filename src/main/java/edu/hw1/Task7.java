package edu.hw1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task7 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task7() {
    }

    public static void main(String[] args) {
        int num;
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Введите число.");
        num = scanner.nextInt();
        LOGGER.info("Количество шагов, чтобы получить число 6174 -> " + (num));
        scanner.close();
    }
}
