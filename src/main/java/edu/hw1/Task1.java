package edu.hw1;

import java.util.Objects;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public final class Task1 {

    private final static int SIXTY = 60;
    private final static Logger LOGGER = LogManager.getLogger();

    private Task1() {
    }

    private static boolean check(String str) {
        Objects.requireNonNull(str);
        boolean res = true;
        if (!str.contains(":") || str.indexOf(':') == 0 || str.indexOf(':') == str.length() - 1
            || !(str.indexOf(':') == str.lastIndexOf(':'))) {
            res = false;
        } else {
            for (int i = 0; i < str.length() && res; i++) {
                if (!(str.charAt(i) >= '0' && str.charAt(i) <= '9') && str.charAt(i) != ':') {
                    res = false;
                }
            }
            if (res) {
                String[] sarr = str.split(":");
                if (Integer.parseInt(sarr[0]) < 0 || Integer.parseInt(sarr[1]) < 0
                    || Integer.parseInt(sarr[1]) >= SIXTY) {
                    res = false;
                }
            }
        }
        return res;
    }

    public static int minutesToSeconds(String str) {
        int res;
        if (!check(str)) {
            res = -1;
        } else {
            String[] sarr = str.split(":");
            res = Integer.parseInt(sarr[0]) * SIXTY + Integer.parseInt(sarr[1]);
        }

        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Введите время(mm:ss).");
        String str = scanner.nextLine();
        LOGGER.info("Длина видео -> " + minutesToSeconds(str));
        scanner.close();

    }
}
