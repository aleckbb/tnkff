package edu.hw3.Task6;

import java.util.PriorityQueue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task6Test {
    @Test
    @DisplayName("Добавление акции без указания её стоимости")
    void test1() {
        Stock stock = new Stock();
        MyMarket myMarket = new MyMarket();
        myMarket.add(stock);
        var actual = myMarket.mostValuableStock();
        assertEquals(stock, actual);
    }

    @Test
    @DisplayName("Добавление акции с положительной ценой")
    void test2() {
        Stock stock = new Stock(1111);
        MyMarket myMarket = new MyMarket();
        myMarket.add(stock);
        var actual = myMarket.mostValuableStock();
        assertEquals(stock, actual);
    }

    @Test
    @DisplayName("Добавление акции с ценой - ноль")
    void test3() {
        Stock stock = new Stock(0);
        MyMarket myMarket = new MyMarket();
        myMarket.add(stock);
        var actual = myMarket.mostValuableStock();
        assertEquals(stock, actual);
    }

    @Test
    @DisplayName("Добавление акции с отрицательной ценой")
    void test4() {
        Stock stock = new Stock(-1999);
        MyMarket myMarket = new MyMarket();
        myMarket.add(stock);
        var actual = myMarket.mostValuableStock();
        assertEquals(stock, actual);
    }

    @Test
    @DisplayName("Инициализация акции отрицательной ценой")
    void test5() {
        Stock stock = new Stock(-1999);
        var actual = stock.getPriceOfAStock();
        assertEquals(0, actual);
    }

    @Test
    @DisplayName("Проверка метода mostValuableStock когда акции есть")
    void test6() {
        MyMarket myMarket = new MyMarket();
        Stock stock1 = new Stock(1111);
        Stock stock2 = new Stock(2222);
        Stock stock3 = new Stock(5555);
        Stock stock4 = new Stock(5555);
        Stock stock5 = new Stock(4444);
        myMarket.add(stock1);
        myMarket.add(stock2);
        myMarket.add(stock3);
        myMarket.add(stock4);
        myMarket.add(stock5);
        var actual = myMarket.mostValuableStock();
        assertEquals(stock3, actual);
    }

    @Test
    @DisplayName("Проверка метода mostValuableStock когда акций нет")
    void test7() {
        MyMarket myMarket = new MyMarket();
        var actual = myMarket.mostValuableStock();
        assertNull(actual);
    }

    @Test
    @DisplayName("Проверка метода remove когда акции есть")
    void test8() {
        MyMarket myMarket = new MyMarket();
        Stock stock1 = new Stock(1111);
        Stock stock2 = new Stock(2222);
        Stock stock3 = new Stock(5555);
        Stock stock4 = new Stock(5555);
        Stock stock5 = new Stock(4444);
        myMarket.add(stock1);
        myMarket.add(stock2);
        myMarket.add(stock3);
        myMarket.add(stock4);
        myMarket.add(stock5);
        PriorityQueue<Stock> priorityQueue = new PriorityQueue<>(myMarket.getPriorityQueue());

        myMarket.remove(stock1);

        boolean flag = true;
        int size = priorityQueue.size();
        for (int i = 0; i < size && flag; ++i) {
            if (priorityQueue.poll() != myMarket.getPriorityQueue().poll()) {
                flag = false;
            }
        }
        assertFalse(flag);
    }

    @Test
    @DisplayName("Проверка метода remove когда акций нет")
    void test9() {
        boolean flag = true;
        MyMarket myMarket = new MyMarket();
        PriorityQueue<Stock> priorityQueue = new PriorityQueue<>(myMarket.getPriorityQueue());

        myMarket.remove(new Stock(1111));

        int size = priorityQueue.size();
        for (int i = 0; i < size && flag; ++i) {
            if (priorityQueue.poll() != myMarket.getPriorityQueue().poll()) {
                flag = false;
            }
        }
        assertTrue(flag);
    }
}
