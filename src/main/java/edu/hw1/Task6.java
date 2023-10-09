package edu.hw1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Comparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task6 {

    private final static Logger LOGGER = LogManager.getLogger();

    private Task6() {
    }

    public static int arrayToNum(Integer[] a) {
        int num = 0;
        for (int i = 0; i < a.length; i++) {
            num = num * 10 + a[i];
        }
        return num;
    }

    public static Integer[] numToArray(int num) {
        Integer[] a = new Integer[4];
        int num1 = num;
        for (int i = 0; i < a.length; i++) {
            a[i] = num1 % 10;
            num1 /= 10;
        }
        return a;
    }

    public static int k(int num) {
        Integer[] a = numToArray(num);
        Integer[] a1 = Arrays.copyOf(a, 4);
        Integer[] a2 = Arrays.copyOf(a, 4);
        Arrays.sort(a1);
        Arrays.sort(a2, Comparator.reverseOrder());
        return arrayToNum(a2) - arrayToNum(a1);
    }

    public static int countK(int num) {
        int res = -1;
        if (num > 1000 && num < 10000) {
            res = 0;
            if (num != 6174) {
                int num1 = k(num);
                if (num1 != 6174) {
                    res = countK(num1);
                }
            }
        }
        return res + 1;
    }

    public static void main(String[] args) {
        int num;
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Введите число.");
        num = scanner.nextInt();
        LOGGER.info("Количество шагов, чтобы получить число 6174 -> " + countK(num));
        scanner.close();
    }

}
