package edu.hw1;

import java.util.Objects;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task4 {

    private final static Logger LOGGER = LogManager.getLogger();

    private Task4() {
    }

    public static String fixString(String str) {
        Objects.requireNonNull(str);
        char tmp;
        char[] a = str.toCharArray();
        if (a.length > 1) {
            for (int i = 0; i < a.length - 1; i += 2) {
                tmp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = tmp;
            }
        }
        return new String(a);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Введите сломанную строку.");
        String str = scanner.nextLine();
        LOGGER.info("Исправленная строка -> " + fixString(str));
        scanner.close();

    }
}
