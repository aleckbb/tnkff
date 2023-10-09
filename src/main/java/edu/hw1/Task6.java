package edu.hw1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task6 {

    final static int FOUR = 4;
    final static int TEN = 10;
    final static int THOUSAND = 1000;

    final static int CONSTK = 6174;

    private final static Logger LOGGER = LogManager.getLogger();

    private Task6() {
    }

    public static int arrayToNum(Integer[] a) {
        int num = 0;
        for (int i = 0; i < a.length; i++) {
            num = num * TEN + a[i];
        }
        return num;
    }

    public static Integer[] numToArray(int num) {
        Integer[] a = new Integer[FOUR];
        int num1 = num;
        for (int i = 0; i < a.length; i++) {
            a[i] = num1 % TEN;
            num1 /= TEN;
        }
        return a;
    }

    public static int k(int num) {
        Integer[] a = numToArray(num);
        Integer[] a1 = Arrays.copyOf(a, FOUR);
        Integer[] a2 = Arrays.copyOf(a, FOUR);
        Arrays.sort(a1);
        Arrays.sort(a2, Comparator.reverseOrder());
        return arrayToNum(a2) - arrayToNum(a1);
    }

    public static int countK(int num) {
        int res = -1;
        if (num > THOUSAND && num < THOUSAND * TEN) {
            res = 0;
            if (num != CONSTK) {
                int num1 = k(num);
                if (num1 != CONSTK) {
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
