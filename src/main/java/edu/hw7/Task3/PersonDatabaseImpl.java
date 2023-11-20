package edu.hw7.Task3;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

public class PersonDatabaseImpl implements PersonDatabase {

    private static final List<Person> PERSON_LIST = new ArrayList<>();

    @Override
    public void add(Person person) {
        PERSON_LIST.add(person);
    }

    @Override
    public void delete(int id) {
        PERSON_LIST.removeIf(person -> person.id() == id);
    }

    private static boolean isFindPerson(Person person) {
        return findByName(person.name()) != null
            && findByAddress(person.address()) != null
            && findByPhone(person.phoneNumber()) != null;
    }

    //@Override
    public static @Nullable Person findByName(String name) {
        for (Person person : PERSON_LIST) {
            if (person.name().equals(name) && isFindPerson(person)) {
                return person;
            }
        }
        return null;
    }

   // @Override
    public static @Nullable Person findByAddress(String address) {
        for (Person person : PERSON_LIST) {
            if (person.address().equals(address) && isFindPerson(person)) {
                return person;
            }
        }
        return null;
    }

    //@Override
    public static @Nullable Person findByPhone(String phone) {
        for (Person person : PERSON_LIST) {
            if (person.phoneNumber().equals(phone) && isFindPerson(person)) {
                return person;
            }
        }
        return null;
    }
}
