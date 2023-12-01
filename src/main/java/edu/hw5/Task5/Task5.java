package edu.hw5.Task5;

import java.util.regex.Pattern;

public class Task5 {

    public static final Pattern RUSSIAN_NUMBER_PATTERN =
        Pattern.compile("^([АВЕКМНОРСТУХ])\\d{3}([АВЕКМНОРСТУХ]){2}\\d{3}$");

    private Task5() {

    }
}
