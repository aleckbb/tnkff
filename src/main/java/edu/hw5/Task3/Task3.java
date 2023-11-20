package edu.hw5.Task3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task3 {
    public static final Pattern FIRST_PATTERN =
        Pattern.compile("^(0|[1-9]\\d*)-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])$");

    public static final Pattern SECOND_PATTERN =
        Pattern.compile("^(0|[1-9]\\d*)-([1-9]|1[0-2])-([1-9]|[1-2]\\d|3[0-1])$");

    public static final Pattern THIRD_PATTERN =
        Pattern.compile("^([1-9]|1[0-2])/([1-9]|[1-2]\\d|3[0-1])/(0\\d|[1-9]\\d)$");

    public static final Pattern FOURTH_PATTERN =
        Pattern.compile("^([1-9]|1[0-2])/([1-9]|[1-2]\\d|3[0-1])/(0|[1-9]\\d*)$");

    public static final Pattern FIFTH_PATTERN =
        Pattern.compile("^tomorrow$");

    public static final Pattern SIXTH_PATTERN =
        Pattern.compile("^today$");

    public static final Pattern SEVENTH_PATTERN =
        Pattern.compile("^yesterday$");

    public static final Pattern EIGHTH_PATTERN =
        Pattern.compile("^1 day ago$");

    public static final Pattern NINTH_PATTERN =
        Pattern.compile("^(1\\d+|[2-9]\\d*) days ago$");

    public static final List<Pattern> LIST_OF_PATTERN =
        List.of(
            FIRST_PATTERN,
            SECOND_PATTERN,
            THIRD_PATTERN,
            FOURTH_PATTERN,
            FIFTH_PATTERN,
            SIXTH_PATTERN,
            SEVENTH_PATTERN,
            EIGHTH_PATTERN,
            NINTH_PATTERN
        );

    public static final int FIRST_PATTERN_MATCH = 0;

    public static final int SECOND_PATTERN_MATCH = 1;

    public static final int THIRD_PATTERN_MATCH = 2;

    public static final int FOURTH_PATTERN_MATCH = 3;

    public static final int FIFTH_PATTERN_MATCH = 4;

    public static final int SIXTH_PATTERN_MATCH = 5;

    public static final int SEVENTH_PATTERN_MATCH = 6;

    public static final int EIGHTH_PATTERN_MATCH = 7;

    private Task3() {

    }

    public static Optional<LocalDate> parseDate(String string) {
        if (string == null) {
            return Optional.empty();
        }
        boolean isMatch = false;
        LocalDate resultDate = null;
        for (int i = 0; i < LIST_OF_PATTERN.size() && !isMatch; ++i) {
            Matcher matcher = LIST_OF_PATTERN.get(i).matcher(string);
            if (matcher.find()) {
                isMatch = true;
                switch (i) {
                    case FIRST_PATTERN_MATCH -> {
                        resultDate = LocalDate.parse(string);
                        break;
                    }
                    case SECOND_PATTERN_MATCH -> {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                        resultDate = LocalDate.parse(string, formatter);
                        break;
                    }
                    case THIRD_PATTERN_MATCH -> {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy");
                        resultDate = LocalDate.parse(string, formatter);
                        break;
                    }
                    case FOURTH_PATTERN_MATCH -> {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
                        resultDate = LocalDate.parse(string, formatter);
                        break;
                    }
                    case FIFTH_PATTERN_MATCH -> {
                        resultDate = LocalDate.now().plusDays(1);
                        break;
                    }
                    case SIXTH_PATTERN_MATCH -> {
                        resultDate = LocalDate.now();
                        break;
                    }
                    case SEVENTH_PATTERN_MATCH, EIGHTH_PATTERN_MATCH -> {
                        resultDate = LocalDate.now().minusDays(1);
                        break;
                    }
                    default -> {
                        String[] parseDate = string.split(" ");
                        resultDate = LocalDate.now().minusDays(Integer.parseInt(parseDate[0]));
                        break;
                    }
                }
            }
        }
        return Optional.ofNullable(resultDate);
    }
}
