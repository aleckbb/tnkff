package edu.hw3.Task6;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class Stock implements Comparable<Stock> {
    private int priceOfAStock;

    public Stock() {
        int intBound = 10000;
        Random random = new Random();
        priceOfAStock = random.nextInt(intBound);
    }

    public Stock(int priceOfAStock) {
        if (priceOfAStock >= 0) {
            this.priceOfAStock = priceOfAStock;
        } else {
            this.priceOfAStock = 0;
        }
    }

    int getPriceOfAStock() {
        return priceOfAStock;
    }

    public int compareTo(@NotNull Stock o) {
        if (o.getPriceOfAStock() > priceOfAStock) {
            return 1;
        } else if (o.getPriceOfAStock() < priceOfAStock) {
            return -1;
        } else {
            return 0;
        }

    }
}
