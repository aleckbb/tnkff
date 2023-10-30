package edu.hw4;

import java.util.Arrays;
import java.util.List;

public class Sample {
    protected final List<List<Animal>> megaUserList;
    protected final List<Animal> userList = Arrays.asList(
        new Animal("Барсик", Animal.Type.CAT, Animal.Sex.M, 10, 20, 5, true),
        new Animal("Дима", Animal.Type.DOG, Animal.Sex.M, 7, 40, 10, true),
        new Animal("Чижик D", Animal.Type.BIRD, Animal.Sex.F, 2, 10, 1, false),
        new Animal("Вася", Animal.Type.FISH, Animal.Sex.F, 3, 5, 1, false),
        new Animal("Гена", Animal.Type.SPIDER, Animal.Sex.M, 77, 20, 2, true),
        new Animal("Саша", Animal.Type.CAT, Animal.Sex.M, 18, 170, 60, true),
        new Animal("Миша", Animal.Type.DOG, Animal.Sex.M, 10, 50, 6, true),
        new Animal("Леха", Animal.Type.SPIDER, Animal.Sex.F, 7, 20, 3, true)

    );

    protected final List<Animal> userList2 = Arrays.asList(
        new Animal("Кентик", Animal.Type.CAT, Animal.Sex.F, 101, 23, 3, true),
        new Animal("Крутыш", Animal.Type.DOG, Animal.Sex.F, 73, 30, 16, true),
        new Animal("Бублик", Animal.Type.BIRD, Animal.Sex.M, 22, 50, 5, false),
        new Animal("Вася Петя", Animal.Type.FISH, Animal.Sex.M, 5, 25, 2, false),
        new Animal("Генадий", Animal.Type.SPIDER, Animal.Sex.F, 3, 10, 3, true),
        new Animal("Сашок", Animal.Type.CAT, Animal.Sex.F, 1, 10, 67, true),
        new Animal("Мишутка", Animal.Type.DOG, Animal.Sex.F, 15, 40, 9, true)
    );

    protected final List<Animal> brokenList = Arrays.asList(
        new Animal("", Animal.Type.CAT, Animal.Sex.F, 101, 23, 3, true),
        new Animal(null, Animal.Type.DOG, Animal.Sex.F, 73, -30, 16, true),
        new Animal("Бублик", null, Animal.Sex.M, 22, 50, 5, false),
        new Animal("Вася Петя", Animal.Type.FISH, null, 5, 25, 2, false),
        new Animal("Генадий", Animal.Type.SPIDER, Animal.Sex.F, -10, 10, 3, true),
        new Animal("Сашок", Animal.Type.CAT, Animal.Sex.F, 1, 10, -67, true),
        new Animal("Мишутка", Animal.Type.DOG, Animal.Sex.F, 15, -40, 9, true)
    );

    private Sample() {
        megaUserList = Arrays.asList(userList, userList2);
    }

    public static void main(String[] args) {
        Sample sample = new Sample();
        Task1.test1(sample);
        Task1.test2(sample, 3);
        Task1.test3(sample);
        Task1.test4(sample);
        Task1.test5(sample);
        Task1.test6(sample);
        Task1.test7(sample, 1);
        Task1.test8(sample, 100);
        Task1.test9(sample);
        Task1.test10(sample);
        Task1.test11(sample);
        Task1.test12(sample);
        Task1.test13(sample);
        Task1.test14(sample, 45);
        Task1.test15(sample, 5, 10);
        Task1.test16(sample);
        Task1.test17(sample);
        Task1.test18(sample);
        Task1.test19(sample);
        Task1.test20(sample);
    }
}
