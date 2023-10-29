package edu.hw4;

import java.util.Comparator;
import java.util.List;

public class Task1 {
    public static void test1(Sample sample) {
        List<Animal> list = sample.userList.stream()
            .sorted(Comparator.comparing(Animal::height))
            .toList();
    }

    public static void test2(Sample sample) {
        List<Animal> list = sample.userList.stream()
            .sorted(Comparator.comparing(Animal::weight)).toList();
    }

    public static void test4(Sample sample) {
        int maxSizeOfName = sample.userList.stream()
            .mapToInt(animal -> animal.name().length())
            .summaryStatistics()
            .getMax();
    }

    public static void test9(Sample sample) {
        int summOfLaws = (int) sample.userList.stream()
            .mapToInt(Animal::paws)
            .summaryStatistics()
            .getSum();
    }

    public static void test10(Sample sample) {
        List<Animal> list = sample.userList.stream()
            .filter(animal -> (animal.paws() != animal.age()))
            .toList();
    }

    public static void test11(Sample sample) {
        List<Animal> list = sample.userList.stream()
            .filter(Animal::bites)
            .filter(animal -> animal.height() > 100)
            .toList();
    }

    public static void test12(Sample sample) {
        List<Animal> list = sample.userList.stream()
            .filter(animal -> animal.height() < animal.weight())
            .toList();
    }

    public static void test16(Sample sample) {
        List<Animal> list = sample.userList.stream()
            .sorted(new TypeComparator()
                .thenComparing(new SexComparator())
                .thenComparing(Animal::name))
            .toList();
    }
}
