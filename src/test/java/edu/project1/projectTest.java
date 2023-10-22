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
        String actual = str.substring(463, 472);
        String expected = "You lost!";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Буква отгадана верно")
    void test3() {
        Game game = new Game("f\ngiveUp\nE");
        game.start("fruct");
        String str = game.getOut();
        String actual = str.substring(114, 118);
        String expected = "Hit!";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Буква отгадана неверно")
    void test4() {
        Game game = new Game("y\ngiveUp\nE");
        game.start("fruct");
        String str = game.getOut();
        String actual = str.substring(114, 141);
        String expected = "Missed, mistake 1 out of 5.";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Повторный ввод буквы")
    void test5() {
        Game game = new Game("ty\ngiveUp\nE");
        game.start("rect");
        String str = game.getOut();
        String actual = str.substring(113, 133);
        String expected = "Input correct letter";
        assertEquals(expected, actual);
    }
}
