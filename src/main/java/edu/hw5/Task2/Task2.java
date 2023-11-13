package edu.hw5.Task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("ParameterAssignment")
public class Task2 {

    private static final int MONTH_IN_YEAR = 12;
    private static final int DESIRED_DATE_IN_MONTH = 13;

    private static final Pattern DATE_PATTERN =
        Pattern.compile("(\\d+)-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])");

    private Task2() {

    }

    public static List<LocalDate> findAllFridayThirteen(int year) {
        List<LocalDate> listOfFridaysThirteenth = new ArrayList<>();
        for (int month = 1; month < MONTH_IN_YEAR; month++) {
            LocalDate date = LocalDate.of(year, month, DESIRED_DATE_IN_MONTH);
            if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                listOfFridaysThirteenth.add(date);
            }
        }
        return listOfFridaysThirteenth;
    }

    public static LocalDate nextFridayThirteen(String dateString) {
        Matcher matcher = DATE_PATTERN.matcher(dateString);
        if (!matcher.find()) {
            return null;
        }
        var datePrepare = dateString.split("-");
        int year = Integer.parseInt(datePrepare[0]);
        int month = Integer.parseInt(datePrepare[1]);
        int day = Integer.parseInt(datePrepare[2]);
        LocalDate currentDate = LocalDate.of(year, month, day);
        List<LocalDate> listOfFridaysThirteenth = findAllFridayThirteen(year);
        if (listOfFridaysThirteenth.isEmpty()) {
            atLeastOneFridayThirteen(listOfFridaysThirteenth, year);
        }

        TemporalAdjuster temporalAdjuster = t -> {
            for (var it : listOfFridaysThirteenth) {
                if (currentDate.getDayOfYear() < it.getDayOfYear()
                    || currentDate.getYear() < it.getYear()) {
                    return it;
                }
            }
            return null;
        };
        LocalDate nextFridayThirteen = currentDate.with(temporalAdjuster);
        if (nextFridayThirteen == null) {
            nextFridayThirteen = findNextFridayThirteenInNextYears(listOfFridaysThirteenth, year);
        }
        return nextFridayThirteen;
    }

    private static LocalDate findNextFridayThirteenInNextYears(List<LocalDate> listOfFridaysThirteenth, int year) {
        year++;
        listOfFridaysThirteenth = findAllFridayThirteen(year);
        if (listOfFridaysThirteenth.isEmpty()) {
            atLeastOneFridayThirteen(listOfFridaysThirteenth, year);
        }
        return listOfFridaysThirteenth.get(0);
    }

    private static void atLeastOneFridayThirteen(List<LocalDate> listOfFridaysThirteenth, int year) {
        while (listOfFridaysThirteenth.isEmpty()) {
            year++;
            listOfFridaysThirteenth = findAllFridayThirteen(year);
        }
    }
}
