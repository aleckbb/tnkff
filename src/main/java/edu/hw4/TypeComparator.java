package edu.hw4;

import java.util.Comparator;

public class TypeComparator implements Comparator<Animal.Type> {
    @Override
    public int compare(Animal.Type o1, Animal.Type o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
