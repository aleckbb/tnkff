package edu.hw3.Task6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task6 {

    private final static Logger LOGGER = LogManager.getLogger();

    Task6() {

    }

    public static void main(String[] args) {
        MyMarket myMarket = new MyMarket();
        for (int i = 0; i < 10; ++i) {

            myMarket.add(new Stock());
        }
        LOGGER.info(myMarket.mostValuableStock().getPriceOfAStock());
    }
}
