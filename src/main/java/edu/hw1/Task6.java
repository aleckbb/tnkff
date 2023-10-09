package edu.hw1;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task6 {

    private final static Logger LOGGER = LogManager.getLogger();

    private Task6() {
    }

    public static int ArrayToNum(Integer[] a) {
        int num = 0;
        for (int i = 0; i < a.length; i++) {
            num = num * 10 + a[i];
        }
        return num;
    }

    public static Integer[] NumToArray(int num) {
        Integer[] a = new Integer[4];
        for (int i = 0; i < a.length; i++) {
            a[i] = num % 10;
            num /= 10;
        }
        return a;
    }

    public static int K(int num) {
        Integer[] a = NumToArray(num);
        Integer[] a1 = Arrays.copyOf(a, 4);
        Integer[] a2 = Arrays.copyOf(a, 4);
        Arrays.sort(a1);
        Arrays.sort(a2, Comparator.reverseOrder());
        num = ArrayToNum(a2) - ArrayToNum(a1);
        return num;
    }

    public static int countK(int num) {
        int res = -1;
        if (num > 1000 && num < 10000) {
            res = 0;
            if (num != 6174) {
                int num1 = K(num);
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
