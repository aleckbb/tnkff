package edu.hw8.Task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("MagicNumber")
public class PasswordGenerator {

    private List<Character> allSignOfPassword = new ArrayList<>();

    private final int passwordLength;

    private char[] currentPassword;

    private boolean isAllZero;

    public PasswordGenerator(int passwordLength) {
        isAllZero = true;
        this.passwordLength = passwordLength;
        currentPassword = new char[passwordLength];
        Arrays.fill(currentPassword, '0');
        setUp();
    }

    private void setUp() {
        for (int i = 0; i < 10; i++) {
            allSignOfPassword.add(((char) ('0' + i)));
        }
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            allSignOfPassword.add(ch);
        }
        for (char ch = 'a'; ch <= 'z'; ch++) {
            allSignOfPassword.add(ch);
        }
    }

    public String nextPassword() {
        int indexOfPassword = passwordLength - 1;

        for (int i = 0; i < passwordLength && isAllZero; ++i) {
            if (currentPassword[i] != '0') {
                isAllZero = false;
            }
        }
        if (isAllZero) {
            isAllZero = false;
            return String.valueOf(currentPassword);
        }
        while (indexOfPassword != -1) {
            if (currentPassword[indexOfPassword] < allSignOfPassword.getLast()) {
                currentPassword[indexOfPassword] =
                    allSignOfPassword.get(allSignOfPassword.indexOf(currentPassword[indexOfPassword]) + 1);
                return String.valueOf(currentPassword);
            } else {
                prepareNextPassword(indexOfPassword);
                indexOfPassword--;
            }
        }
        return "";
    }

    private void prepareNextPassword(int start) {
        for (int i = start; i < passwordLength; ++i) {
            currentPassword[i] = '0';
        }
    }
}
