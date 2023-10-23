package edu.hw3.Task1;

import java.util.HashMap;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task1 {
    private final static Logger LOGGER = LogManager.getLogger();

    private static HashMap<Character, Character> hashMap = new HashMap<>();

    private Task1() {
    }

    public static void setHashMap() {
        for (char i = 'a', j = 'z'; i < j; ++i, --j) {
            hashMap.put(i, j);
            hashMap.put(j, i);
        }
        for (char i = 'A', j = 'Z'; i < j; ++i, --j) {
            hashMap.put(i, j);
            hashMap.put(j, i);
        }
    }

    public static String atbash(String str) {
        setHashMap();
        String str1 = str;
        for (int i = 0; i < str.length(); i++) {
            if (hashMap.get(str.charAt(i)) != null) {
                str1 = str1.substring(0, i) + hashMap.get(str.charAt(i)) + str1.substring(i + 1);
            }
        }
        return str1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Input a string: ");
        String str = scanner.nextLine();
        LOGGER.info(atbash(str));
        scanner.close();
    }
}
