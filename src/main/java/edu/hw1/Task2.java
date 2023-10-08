package edu.hw1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task2 {

    final static int TEN = 10;
    private final static Logger LOGGER = LogManager.getLogger();

    private Task2() {
    }

    public static int countDigits(int digits) {
        int res = 0;
        int absDigits = Math.abs(digits);
        if (absDigits == 0) {
            res = 1;
        }
        while (absDigits > 0) {
            absDigits /= TEN;
            res++;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Введите число.");
        int digits = scanner.nextInt();
        LOGGER.info("Длина числа -> " + countDigits(digits));
        scanner.close();
    }
}
