package edu.hw5.Task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task2Test {

    private static final Pattern DATE_PATTERN =
        Pattern.compile("(\\d+)-(0[1-9]|1[0-2])-(0[1-9]|[1-2]\\d|3[0-1])");

    @Test
    @DisplayName("Метод findAllFridayThirteen возвращает корректные даты")
    void test1() {
        // Given
        int year = 2004;
        List<LocalDate> listOfFridaysThirteenth = Task2.findAllFridayThirteen(year);
        boolean isCorrectDate = true;

        // When
        for (var it : listOfFridaysThirteenth) {
            Matcher matcher = DATE_PATTERN.matcher(it.toString());
            if (!matcher.find()) {
                isCorrectDate = false;
                break;
            }
        }

        // Then
        assertTrue(isCorrectDate);
    }

    @Test
    @DisplayName("Метод nextFridayThirteen возвращает корректную дату")
    void test2() {
        // Given
        String dateString = "2022-07-07";
        LocalDate nextFridayThirteen = Task2.nextFridayThirteen(dateString);
        boolean isCorrectDate = true;

        // When
        Matcher matcher = DATE_PATTERN.matcher(nextFridayThirteen.toString());
        if (!matcher.find()) {
            isCorrectDate = false;
        }

        // Then
        assertTrue(isCorrectDate);
    }

    @Test
    @DisplayName("Метод nextFridayThirteen возвращает следующую пятницу 13")
    void test3() {
        // Given
        String strDate = "2022-07-07";
        LocalDate nextFridayThirteen = Task2.nextFridayThirteen(strDate);
        String[] ymd = strDate.split("-");
        LocalDate currentDate =
            LocalDate.of(Integer.parseInt(ymd[0]), Integer.parseInt(ymd[1]), Integer.parseInt(ymd[2]));
        boolean isCorrectDate = true;

        // When
        if (currentDate.getYear() > nextFridayThirteen.getYear()
            || currentDate.getYear() == nextFridayThirteen.getYear()
            && currentDate.getDayOfYear() > nextFridayThirteen.getDayOfYear()) {
            isCorrectDate = false;
        }

        // Then
        assertTrue(isCorrectDate);
    }

    @Test
    @DisplayName("Метод findAllFridayThirteen возвращает пятницы 13е")
    void test4() {
        // Given
        int year = 2023;
        List<LocalDate> listOfFridaysThirteenth = Task2.findAllFridayThirteen(year);
        boolean isCorrectDate = true;

        // When
        for (var it : listOfFridaysThirteenth) {
            if (!it.getDayOfWeek().equals(DayOfWeek.FRIDAY) && it.getDayOfMonth() != 13) {
                isCorrectDate = false;
                break;
            }
        }

        // Then
        assertTrue(isCorrectDate);
    }

    @Test
    @DisplayName("Метод nextFridayThirteen возвращает пятницу 13ю")
    void test5() {
        // Given
        String strDate = "2022-07-07";
        LocalDate nextFridayThirteen = Task2.nextFridayThirteen(strDate);
        boolean isCorrectDate = true;

        // When
        if (!nextFridayThirteen.getDayOfWeek().equals(DayOfWeek.FRIDAY) && nextFridayThirteen.getDayOfMonth() != 13) {
            isCorrectDate = false;
        }

        // Then
        assertTrue(isCorrectDate);
    }

    @Test
    @DisplayName("Метод nextFridayThirteen получил некорректную дату")
    void test6() {
        // Given
        String strDate = "2022-17-07";
        LocalDate nextFridayThirteen = Task2.nextFridayThirteen(strDate);

        // Then
        assertNull(nextFridayThirteen);
    }

    @Test
    @DisplayName("Метод findAllFridayThirteen получил отрицательный год")
    void test7() {
        // Given
        int year = -2023;
        List<LocalDate> listOfFridaysThirteenth = Task2.findAllFridayThirteen(year);

        // Then
        assertNotNull(listOfFridaysThirteenth);
    }

    @Test
    @DisplayName("Метод findAllFridayThirteen получил нулевой год")
    void test8() {
        // Given
        int year = 0;
        List<LocalDate> listOfFridaysThirteenth = Task2.findAllFridayThirteen(year);

        // Then
        assertNotNull(listOfFridaysThirteenth);
    }
}
