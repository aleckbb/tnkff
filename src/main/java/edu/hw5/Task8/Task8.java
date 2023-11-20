package edu.hw5.Task8;

import java.util.regex.Pattern;

public class Task8 {
    public static final Pattern FIRST_PATTERN = Pattern.compile("^[01]([01]{2})*$"); // нечетная длина

    public static final Pattern SECOND_PATTERN = Pattern.compile("^0([01]{2})*$|^1[01]([01]{2})*$");
    //начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину

    public static final Pattern THIRD_PATTERN =
        Pattern.compile("^((1?[0][0][0]*)|([0]1?[0][0]*)|([0][0]1?[0]*)|([0][0][0]*1?))$");
    // содержит не менее двух 0 и не более одной 1

    public static final Pattern FOURS_PATTERN = Pattern.compile("^([01]|([1]?[0]+|1[0]+[1]?)*)$");
        // нет последовательных 1

    private Task8() {

    }
}
