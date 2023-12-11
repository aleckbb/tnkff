package edu.hw10.Task2.Fact;

public class FactCalculatorImp implements FactCalculator {
    @Override
    public long fact(int number) {
        if (number < 2) {
            return 1;
        }
        return number * fact(number - 1);
    }
}
