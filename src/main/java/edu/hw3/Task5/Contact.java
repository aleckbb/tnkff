package edu.hw3.Task5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class Contact {

    private final static Logger LOGGER = LogManager.getLogger();
    private String name;
    private String surname;

    protected Contact(String name, String surname) {
        if (surname.isEmpty()) {
            this.name = name;
            this.surname = "";
        } else {
            this.name = name;
            this.surname = surname;
        }
    }

    public void print() {
        LOGGER.info(name + (surname.isEmpty() ? "" : " " + surname));
    }

    public int compareToASC(@NotNull Contact o) {
        if (surname.isEmpty() && o.surname.isEmpty()) {
            return name.compareTo(o.name);
        } else if (surname.isEmpty()) {
            return name.compareTo(o.surname);
        } else if (o.surname.isEmpty()) {
            return surname.compareTo(o.name);
        } else {
            return surname.compareTo(o.surname);
        }
    }

    public int compareToDESC(@NotNull Contact o) {
        if (surname.isEmpty() && o.surname.isEmpty()) {
            return o.name.compareTo(name);
        } else if (surname.isEmpty()) {
            return o.surname.compareTo(name);
        } else if (o.surname.isEmpty()) {
            return o.name.compareTo(surname);
        } else {
            return o.surname.compareTo(surname);
        }
    }
}
