package edu.hw10.Task2.Fact;

import edu.hw10.Task2.Annotation.Cache;

public interface FactCalculator {
    @Cache(persist = true)
    public long fact(int number);
}
