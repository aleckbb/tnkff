package edu.hw7.Task3;

import org.jetbrains.annotations.Nullable;

interface PersonDatabase {
    void add(Person person);

    void delete(int id);

    static @Nullable Person findByName(String name) {
        return null;
    }

    static @Nullable Person findByAddress(String address) {
        return null;
    }

    static @Nullable Person findByPhone(String phone) {
        return null;
    }
}
