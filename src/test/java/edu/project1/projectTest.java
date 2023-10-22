package edu.project1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class projectTest {
    @Test
    @DisplayName("Проверка на пустую строку")
    void test1() {
        Game game = new Game();
        boolean actual = game.start("");
        boolean expected = false;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Превышено количество попыток")
    void test2() {
        Game game = new Game("a\nb\nq\nd\ne\nE");
        game.start("fruct");
        String str = game.getOut();
        int index = str.indexOf("You lost!");
        String actual = str.substring(index, index + 9);
        String expected = "You lost!";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Буква отгадана верно")
    void test3() {
        Game game = new Game("f\ngiveUp\nE");
        game.start("fruct");
        String str = game.getOut();
        int index = str.indexOf("Hit!");
        String actual = str.substring(index, index + 4);
        String expected = "Hit!";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Буква отгадана неверно")
    void test4() {
        Game game = new Game("y\ngiveUp\nE");
        game.start("fruct");
        String str = game.getOut();
        int index = str.indexOf("Missed, mistake 1 out of 5.");
        String actual = str.substring(index, index + 26);
        String expected = "Missed, mistake 1 out of 5";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Повторный ввод буквы")
    void test5() {
        Game game = new Game("ty\ngiveUp\nE");
        game.start("rect");
        String str = game.getOut();
        int index = str.indexOf("Input correct letter");
        String actual = str.substring(index, index + 20);
        String expected = "Input correct letter";
        assertEquals(expected, actual);
    }
}
