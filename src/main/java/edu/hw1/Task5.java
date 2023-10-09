package edu.hw1;

import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task5 {

    final static int TEN = 10;
    private final static Logger LOGGER = LogManager.getLogger();

    private Task5() {
    }

    public static boolean isPalindrome(int num) {
        boolean res = true;
        if (num / TEN != 0) {
            int num1 = num;
            int tmp = 0;
            while (num1 > 0) {
                tmp = tmp * TEN + num1 % TEN;
                num1 /= TEN;
            }
            if (num != tmp) {
                res = false;
            }
        }
        return res;
    }

    public static boolean isPalindromeDescendant(int num) {
        boolean res = true;
        if (!isPalindrome(num)) {
            String str = String.valueOf(num);
            String descendant = str;
            if (str.length() < 2 || str.length() % 2 == 1) {
                res = false;
            } else {
                res = false;
                while (!res && str.length() >= 2 && str.length() % 2 == 0) {
                    descendant = "";
                    for (int i = 0; i < str.length() - 1; i += 2) {
                        descendant = descendant + ((str.charAt(i) - '0')
                            + (str.charAt(i + 1) - '0'));
                    }
                    if (descendant.length() >= 2 && isPalindrome(Integer.parseInt(descendant))) {
                        res = true;
                    }
                    str = descendant;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Введите число.");
        int num = scanner.nextInt();
        LOGGER.info("Число или его потомок являются полиндромом -> " + isPalindromeDescendant(num));
        scanner.close();

    }
}
