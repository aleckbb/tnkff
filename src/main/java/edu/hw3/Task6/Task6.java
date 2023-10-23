package edu.hw3.Task6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task6 {

    private final static Logger LOGGER = LogManager.getLogger();

    private Task6() {

    }

    public static void main(String[] args) {
        MyMarket myMarket = new MyMarket();
        int size = 10;
        for (int i = 0; i < size; ++i) {
            myMarket.add(new Stock());
        }
        LOGGER.info(myMarket.mostValuableStock().getPriceOfAStock());
    }
}
