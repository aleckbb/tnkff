package edu.hw7.Task2;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Task2 {

    private Task2() {

    }

    public static BigInteger factorialOfNumber(int num) {
        if (num <= 1) {
            return new BigInteger("1");
        }
        List<BigInteger> list = new ArrayList<>();
        for (int i = 2; i <= num; ++i) {
            list.add(new BigInteger(String.valueOf(i)));
        }
        return list.parallelStream().reduce(new BigInteger("1"), BigInteger::multiply);
    }
}
