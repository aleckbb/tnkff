package edu.hw10.Task2.Fib;

import edu.hw10.Task2.Annotation.Cache;

public interface FibCalculator {
    @Cache(persist = true)
    public long fib(int number);
}
