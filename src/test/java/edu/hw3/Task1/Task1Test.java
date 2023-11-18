package edu.hw3.Task1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task1Test {

    @Test
    @DisplayName("Примеры из дз")
    void test1() {
        String actual = "Hello world!";
        assertEquals("Svool dliow!", Task1.atbash(actual));

        actual = "Any fool can write code that a computer can understand." +
            " Good programmers write code that humans can understand. ― Martin Fowler";
        assertEquals("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw." +
            " Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi", Task1.atbash(actual));
    }

    @Test
    @DisplayName("Пустая строка")
    void test2() {
        String actual = "";
        assertEquals("", Task1.atbash(actual));
    }

    @Test
    @DisplayName("Строка, которая не содержит латинских букв")
    void test3() {
        String actual = "абв!,?";
        assertEquals("абв!,?", Task1.atbash(actual));
    }

    @Test
    @DisplayName("Проверка на верхний регистр")
    void test4() {
        String actual = "A";
        assertEquals("Z", Task1.atbash(actual));
    }

    @Test
    @DisplayName("Проверка на нижний регистр")
    void test5() {
        String actual = "a";
        assertEquals("z", Task1.atbash(actual));
    }
}
