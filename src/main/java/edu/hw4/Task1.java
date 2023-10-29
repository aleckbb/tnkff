package edu.hw4;

import java.util.Comparator;

public class Task1 {
    public static void test1(Sample sample){
        sample.userList.stream()
            .sorted(Comparator.comparing(Animal::height))
            .forEach(System.out::println);
    }

    public static void test16(Sample sample){
        sample.userList.stream()
            .sorted(new TypeComparator())
                //.thenComparing(Animal::sex)
                //.thenComparing(Animal::name))
            .forEach(System.out::println);
    }
}
