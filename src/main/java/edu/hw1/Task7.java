package edu.hw1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task7 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task7() {
    }

    public static int rotateLeft(int n, int shift) {
        int res = -1;
        if (n >= 0) {
            if (shift < 0) {
                res = rotateRight(n, -shift);
            } else {
                String str = Integer.toBinaryString(n);
                for (int i = 0; i < shift; i++) {
                    str = str.substring(1, str.length()) + str.charAt(0);
                }
                res = Integer.parseInt(str, 2);
            }
        }
        return res;
    }

    public static int rotateRight(int n, int shift) {
        int res = -1;
        if (n >= 0) {
            if (shift < 0) {
                res = rotateLeft(n, -shift);
            } else {
                String str = Integer.toBinaryString(n);
                for (int i = 0; i < shift; i++) {
                    str = str.charAt(str.length() - 1) + str.substring(0, str.length() - 1);
                }
                res = Integer.parseInt(str, 2);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n, shift;
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Введите целое положительное число и размер циклического сдвига.");
        n = scanner.nextInt();
        shift = scanner.nextInt();
        LOGGER.info("Результат левого сдвига -> " + rotateLeft(n, shift));
        LOGGER.info("Результат правого сдвига -> " + rotateRight(n, shift));
        scanner.close();
    }
}
