package edu.hw3.Task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task4Test {

    @Test
    @DisplayName("Примеры из дз")
    void test1() {
        Task4.setHashMap();
        assertEquals("II", Task4.convertToRoman(2));

        assertEquals("XII", Task4.convertToRoman(12));

        assertEquals("XVI", Task4.convertToRoman(16));
    }

    @Test
    @DisplayName("Нижняя граница")
    void test2() {
        Task4.setHashMap();
        assertEquals("", Task4.convertToRoman(0));
    }

    @Test
    @DisplayName("Отрицательное число")
    void test3() {
        Task4.setHashMap();
        assertEquals("", Task4.convertToRoman(-1));
    }

    @Test
    @DisplayName("Верхняя граница")
    void test4() {
        Task4.setHashMap();
        assertEquals("", Task4.convertToRoman(4000));
    }


    @Test
    @DisplayName("Числа с цифрами 4 и 9")
    void test5() {
        Task4.setHashMap();
        assertEquals("IV", Task4.convertToRoman(4));

        assertEquals("IX", Task4.convertToRoman(9));

        assertEquals("XL", Task4.convertToRoman(40));

        assertEquals("XC", Task4.convertToRoman(90));

        assertEquals("CD", Task4.convertToRoman(400));

        assertEquals("CM", Task4.convertToRoman(900));
    }

    @Test
    @DisplayName("Ещё несколько примеров")
    void test6() {
        Task4.setHashMap();
        assertEquals("MCCXXXIV", Task4.convertToRoman(1234));

        assertEquals("MMMCCCXXXIII", Task4.convertToRoman(3333));

        assertEquals("MMMCMXCIX", Task4.convertToRoman(3999));
    }
}
