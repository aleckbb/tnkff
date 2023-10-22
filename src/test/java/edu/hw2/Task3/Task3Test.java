package edu.hw2.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task3Test {
    @DisplayName("Проверка команд с DefaultConnectionManager")
    @ParameterizedTest()
    @CsvSource({
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
    })
    void test1(String command) throws Exception {
        boolean actual = false;
        boolean expected = true;
        try {
            Task3.PopularCommandExecutor executor = new Task3.PopularCommandExecutor(3, true);
            executor.tryExecute(command);
            actual = true;
        } catch (Task3.ConnectionException e) {
            expected = false;
        }
        assertEquals(expected, actual);
    }

    @DisplayName("Проверка команд с FaultyConnectionManager")
    @ParameterizedTest()
    @CsvSource({
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
    })
    void test2(String command) throws Exception {
        boolean actual = false;
        boolean expected = true;
        try {
            Task3.PopularCommandExecutor executor = new Task3.PopularCommandExecutor(3, false);
            executor.tryExecute(command);
            actual = true;
        } catch (Task3.ConnectionException e) {
            expected = false;
        }
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("maxAttempts == 0")
    void test3() throws Exception {
        boolean actual = false;
        boolean expected = true;
        try {
            Task3.PopularCommandExecutor executor = new Task3.PopularCommandExecutor(0, true);
            executor.updatePackages();
            actual = true;
        } catch (Task3.ConnectionException e) {
            expected = false;
        }
        assertEquals(expected, actual);
    }
}
