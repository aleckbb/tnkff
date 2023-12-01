package edu.hw5.Task6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task6 {

    private Task6() {

    }

    public static boolean isSubsequence(String subsequence, String str) {
        if (str == null || subsequence == null || str.isEmpty() || subsequence.isEmpty()) {
            return false;
        }
        var subsequenceArray = subsequence.split("");
        StringBuilder pattern = new StringBuilder();
        for (var ch : subsequenceArray) {
            pattern.append(ch);
            pattern.append("(\\w*)");
        }
        Pattern patternSubsequence = Pattern.compile(pattern.toString());
        Matcher matcher = patternSubsequence.matcher(str);
        return matcher.find();
    }
}
