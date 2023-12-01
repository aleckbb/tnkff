package edu.hw8.Task3;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MagicNumber")
public class PasswordGenerator {

    private List<Character> allSignOfPassword = new ArrayList<>();

    private final int maxPasswordLength;

    private volatile Character[] currentPassword;

    private volatile int shift;

    public PasswordGenerator(int maxPasswordLength) {
        shift = maxPasswordLength - 1;
        this.maxPasswordLength = maxPasswordLength;
        currentPassword = new Character[maxPasswordLength];
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

    public synchronized String nextPassword() {
        StringBuilder nextPassword = new StringBuilder();
        int indexOfPassword = maxPasswordLength - 1;
        if (currentPassword[indexOfPassword] == null) {
            currentPassword[indexOfPassword] = '0';
            return String.valueOf(currentPassword[indexOfPassword]);
        }
        while (indexOfPassword != -1) {
            if (currentPassword[indexOfPassword] < allSignOfPassword.getLast()) {
                currentPassword[indexOfPassword] =
                    allSignOfPassword.get(allSignOfPassword.indexOf(currentPassword[indexOfPassword]) + 1);
                return createPassword(nextPassword, shift);
            } else {
                if (indexOfPassword - 1 != -1 && currentPassword[indexOfPassword - 1] == null) {
                    currentPassword[indexOfPassword - 1] = '0';
                    prepareNextPassword(indexOfPassword);
                    --shift;
                    return createPassword(nextPassword, shift);
                }
                prepareNextPassword(indexOfPassword);
                indexOfPassword--;
            }
        }
        return "";
    }

    private synchronized String createPassword(StringBuilder nextPassword, int index) {
        for (int i = index; i < maxPasswordLength; ++i) {
            nextPassword.append(currentPassword[i]);
        }
        return nextPassword.toString();
    }

    private synchronized void prepareNextPassword(int start) {
        for (int i = start; i < maxPasswordLength; ++i) {
            currentPassword[i] = '0';
        }
    }
}
