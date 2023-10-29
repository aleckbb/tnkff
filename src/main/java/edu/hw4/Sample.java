package edu.hw4;

import java.util.Arrays;
import java.util.List;

public class Sample {
    protected final List<Animal> userList = Arrays.asList(
        new Animal("Барсик", Animal.Type.CAT, Animal.Sex.M, 10, 20, 5, true),
        new Animal("Дима", Animal.Type.DOG, Animal.Sex.M, 7, 40, 10, true),
        new Animal("Чижик", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false),
        new Animal("Вася", Animal.Type.FISH, Animal.Sex.F, 3, 5, 1, false),
        new Animal("Гена", Animal.Type.SPIDER, Animal.Sex.M, 77, 20, 2, true),
        new Animal("Саша", Animal.Type.CAT, Animal.Sex.M, 18, 170, 60, true),
        new Animal("Миша", Animal.Type.DOG, Animal.Sex.M, 10, 50, 6, true)
    );

    public static void main(String[] args) {
        Sample sample = new Sample();
        Task1.test1(sample);
        Task1.test4(sample);
        Task1.test9(sample);
        Task1.test10(sample);
        Task1.test11(sample);
        Task1.test12(sample);
        Task1.test16(sample);

    }




}
