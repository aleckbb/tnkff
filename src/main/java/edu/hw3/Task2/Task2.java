package edu.hw3.Task2;

import java.util.ArrayList;
import java.util.Stack;

public class Task2 {
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";

    private Task2() {

    }

    public static String[] clusterize(String s) {
        Stack<String> stack = new Stack<>();
        ArrayList<String> clusterizeStr = new ArrayList<>();
        String tmp = "";
        for (int i = 0; i < s.length(); ++i) {
            if (OPEN_BRACKET.contains(Character.toString(s.charAt(i)))) {
                stack.add(s.substring(i, i + 1));
                tmp += "(";
            } else if (CLOSE_BRACKET.contains(Character.toString(s.charAt(i)))) {
                if (stack.empty()) {
                    return new String[] {};
                } else {
                    stack.pop();
                    tmp += ")";
                    if (stack.empty()) {
                        clusterizeStr.add(tmp);
                        tmp = "";
                    }
                }
            }
        }
        if (!stack.empty()) {
            return new String[] {};
        }
        String[] newStr = new String[clusterizeStr.size()];
        for (int i = 0; i < clusterizeStr.size(); ++i) {
            newStr[i] = clusterizeStr.get(i);
        }
        return newStr;
    }
}
