package edu.project3.Printer;

import edu.project3.model.Statistic;

public interface Printer {
    <T> String print(Statistic<T> statistic);
}
