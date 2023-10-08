package edu.hw1;

import java.util.Objects;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task3 {
    private final static Logger LOGGER = LogManager.getLogger();

    private Task3() {
    }

    public static int maxArray(int[] a) {
        Objects.requireNonNull(a);
        int max = a[0];
        for (int j = 1; j < a.length; j++) {
            if (a[j] > max) {
                max = a[j];
            }
        }
        return max;
    }

    public static int minArray(int[] a) {
        Objects.requireNonNull(a);
        int min = a[0];
        for (int j = 1; j < a.length; j++) {
            if (a[j] < min) {
                min = a[j];
            }
        }
        return min;
    }

    public static boolean isNestable(int[] a1, int[] a2) {
        return (minArray(a1) > minArray(a2) && maxArray(a1) < maxArray(a2));
    }

    public static void main(String[] args) {
        int n;
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Введите количество элементов в первом массиве, затем сами элементы.");
        n = scanner.nextInt();
        int[] a1 = new int[n];
        for (int i = 0; i < n; i++) {
            a1[i] = scanner.nextInt();
        }
        LOGGER.info("Введите количество элементов во втором массиве, затем сами элементы.");
        n = scanner.nextInt();
        int[] a2 = new int[n];
        for (int i = 0; i < n; i++) {
            a2[i] = scanner.nextInt();
        }
        LOGGER.info(isNestable(a1, a2));
    }
}
