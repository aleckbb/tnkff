package edu.hw5.Task1;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    private static final int MINUTES_OF_HOUR = 60;
    private static final int HOUR_OF_DAY = 24;
    private static final int GROUP_YEARS = 1;
    private static final int GROUP_MONTHS = 2;
    private static final int GROUP_DAYS = 3;
    private static final int GROUP_HOURS = 4;
    private static final int GROUP_MINUTES = 5;
    private static final String SESSIONS = """
        2022-03-12, 20:20 - 2022-03-12, 23:50
        2022-04-01, 21:30 - 2022-04-02, 01:20
        """;
    private static final String SESSION_PATTERN =
        "(\\d+)-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1]), ([0-1]\\d|2[0-3]):([0-5]\\d)";

    private static final String DASH = " - ";

    private static final String COLON = ":";

    private Task1() {

    }

    public static String averageSessionDuration(String sessions) {
        if (!isValidSessions(sessions) || !isCorrectSessions(sessions)) {
            return "Данные введены некорректно";
        }
        List<String> sessionsList = sessions.lines().toList();
        Pattern timePattern = Pattern.compile("([0-1]\\d|2[0-4]):[0-5]\\d");
        String[] timeStart;
        String[] timeEnd;
        int timeStartOfMinutes;
        int timeEndOfMinutes;
        int allSessionsDurationOfMinutes = 0;
        for (String s : sessionsList) {
            var session = s.split(DASH);
            Matcher matcher1 = timePattern.matcher(session[0]);
            Matcher matcher2 = timePattern.matcher(session[1]);
            if (matcher1.find() && matcher2.find()) {
                timeStart = matcher1.group().split(COLON);
                timeEnd = matcher2.group().split(COLON);
                timeStartOfMinutes = Integer.parseInt(timeStart[0]) * MINUTES_OF_HOUR + Integer.parseInt(timeStart[1]);
                timeEndOfMinutes = Integer.parseInt(timeEnd[0]) * MINUTES_OF_HOUR + Integer.parseInt(timeEnd[1]);
                if (timeEndOfMinutes < timeStartOfMinutes) {
                    timeEndOfMinutes += MINUTES_OF_HOUR * HOUR_OF_DAY;
                }
                allSessionsDurationOfMinutes += timeEndOfMinutes - timeStartOfMinutes;
            }
        }
        int averageSessionDuration = allSessionsDurationOfMinutes / (sessionsList.size());
        return (averageSessionDuration / MINUTES_OF_HOUR) + COLON + (averageSessionDuration % MINUTES_OF_HOUR);
    }

    public static boolean isCorrectSessions(String sessions) {
        List<String> sessionsList = sessions.lines().toList();
        for (String s : sessionsList) {
            var session = s.split(DASH);
            Pattern sessionPattern = Pattern.compile(SESSION_PATTERN);
            Matcher matcher1 = sessionPattern.matcher(session[0]);
            Matcher matcher2 = sessionPattern.matcher(session[1]);
            if (matcher1.find() && matcher2.find()) {
                if (isCorrectYears(matcher1, matcher2)
                    || isCorrectMonths(matcher1, matcher2)
                    || isCorrectDays(matcher1, matcher2)
                    || isCorrectHours(matcher1, matcher2)
                    || isCorrectMinutes(matcher1, matcher2)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean isCorrectYears(Matcher matcher1, Matcher matcher2) {
        return Integer.parseInt(matcher1.group(GROUP_YEARS)) > Integer.parseInt(matcher2.group(GROUP_YEARS));
    }

    private static boolean isCorrectMonths(Matcher matcher1, Matcher matcher2) {
        return Integer.parseInt(matcher1.group(GROUP_YEARS)) == Integer.parseInt(matcher2.group(GROUP_YEARS))
            && Integer.parseInt(matcher1.group(GROUP_MONTHS)) > Integer.parseInt(matcher2.group(GROUP_MONTHS));
    }

    private static boolean isCorrectDays(Matcher matcher1, Matcher matcher2) {
        return Integer.parseInt(matcher1.group(GROUP_YEARS)) == Integer.parseInt(matcher2.group(GROUP_YEARS))
            && Integer.parseInt(matcher1.group(GROUP_MONTHS)) == Integer.parseInt(matcher2.group(GROUP_MONTHS))
            && Integer.parseInt(matcher1.group(GROUP_DAYS)) > Integer.parseInt(matcher2.group(GROUP_DAYS));
    }

    private static boolean isCorrectHours(Matcher matcher1, Matcher matcher2) {
        return Integer.parseInt(matcher1.group(GROUP_YEARS)) == Integer.parseInt(matcher2.group(GROUP_YEARS))
            && Integer.parseInt(matcher1.group(GROUP_MONTHS)) == Integer.parseInt(matcher2.group(GROUP_MONTHS))
            && Integer.parseInt(matcher1.group(GROUP_DAYS)) == Integer.parseInt(matcher2.group(GROUP_DAYS))
            && Integer.parseInt(matcher1.group(GROUP_HOURS)) > Integer.parseInt(matcher2.group(GROUP_HOURS));
    }

    private static boolean isCorrectMinutes(Matcher matcher1, Matcher matcher2) {
        return Integer.parseInt(matcher1.group(GROUP_YEARS)) == Integer.parseInt(matcher2.group(GROUP_YEARS))
            && Integer.parseInt(matcher1.group(GROUP_MONTHS)) == Integer.parseInt(matcher2.group(GROUP_MONTHS))
            && Integer.parseInt(matcher1.group(GROUP_DAYS)) == Integer.parseInt(matcher2.group(GROUP_DAYS))
            && Integer.parseInt(matcher1.group(GROUP_HOURS)) == Integer.parseInt(matcher2.group(GROUP_HOURS))
            && Integer.parseInt(matcher1.group(GROUP_MINUTES)) > Integer.parseInt(matcher2.group(GROUP_MINUTES));
    }

    public static boolean isValidSessions(String sessions) {
        if (sessions == null || sessions.isEmpty()) {
            return false;
        }
        List<String> sessionsList = sessions.lines().toList();
        for (String s : sessionsList) {
            var session = s.split(DASH);
            Pattern sessionPattern = Pattern.compile(SESSION_PATTERN);
            Matcher matcher1 = sessionPattern.matcher(session[0]);
            Matcher matcher2 = sessionPattern.matcher(session[1]);
            if (!matcher1.find() || !matcher2.find()) {
                return false;
            }
        }
        return true;
    }
}
