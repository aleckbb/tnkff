package edu.hw5.Task7;

import java.util.regex.Pattern;

public class Task7 {
    public static final Pattern FIRST_PATTERN = Pattern.compile("[0-1]{2}0([0-1]+)?");

    public static final Pattern SECOND_PATTERN = Pattern.compile("^1([0-1]+)?1$|^0([0-1]+)?0$");

    public static final Pattern THIRD_PATTERN = Pattern.compile("^[0-1]([0-1]{1,2})?$");

    private Task7() {

    }
}
