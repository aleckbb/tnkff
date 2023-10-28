package edu.hw3.Task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Task5Test {

    @Test
    @DisplayName("Примеры из дз(пустой массив, null, сортировка по возрастанию, сортировка по убыванию)")
    void test1() {
        Assertions.assertNull(Task5.parseContacts(new String[]{}, "DESC"));

        Assertions.assertNull(Task5.parseContacts(null, "DESC"));

        var actual = Task5.parseContacts(new String[]{"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"},
            "ASC");
        var expexted = new Contact[]{
            new Contact("Thomas", "Aquinas"), new Contact("Rene", "Descartes"),
            new Contact("David", "Hume"), new Contact("John", "Locke")
        };
        boolean fl = true;
        for(int i = 0; i < actual.length; ++i){
            if(actual[i].compareToASC(expexted[i]) != 0){
                fl = false;
            }
        }
        Assertions.assertTrue(fl);

        actual = Task5.parseContacts(new String[]{"Paul Erdos", "Leonhard Euler", "Carl Gauss"},
            "DESC");
        expexted = new Contact[]{
            new Contact("Carl", "Gauss"), new Contact("Leonhard", "Euler"),
            new Contact("Paul", "Erdos")
        };
        for(int i = 0; i < actual.length; ++i){
            if(actual[i].compareToASC(expexted[i]) != 0){
                fl = false;
            }
        }
        Assertions.assertTrue(fl);
    }

    @Test
    @DisplayName("Нет фамилии у одного из контактов(у 1-го, у 2-го)")
    void test2() {
        var actual = Task5.parseContacts(new String[]{"John", "Thomas Aquinas"},
            "ASC");
        var expexted = new Contact[]{
            new Contact("Thomas", "Aquinas"), new Contact("John", "")
        };
        boolean fl = true;
        for(int i = 0; i < actual.length; ++i){
            if(actual[i].compareToASC(expexted[i]) != 0){
                fl = false;
            }
        }
        Assertions.assertTrue(fl);

        actual = Task5.parseContacts(new String[]{"John Locke", "Thomas"},
            "ASC");
        expexted = new Contact[]{
            new Contact("John", "Locke"), new Contact("Thomas", "")
        };
        for(int i = 0; i < actual.length; ++i){
            if(actual[i].compareToASC(expexted[i]) != 0){
                fl = false;
            }
        }
        Assertions.assertTrue(fl);
    }

    @Test
    @DisplayName("Нет фамилии у обоих контактов")
    void test3() {
        var actual = Task5.parseContacts(
            new String[] {"John", "Thomas"},
            "ASC"
        );
        var expexted = new Contact[] {
            new Contact("John", ""), new Contact("Thomas", "")
        };
        boolean fl = true;
        for (int i = 0; i < actual.length; ++i) {
            if (actual[i].compareToASC(expexted[i]) != 0) {
                fl = false;
            }
        }
        Assertions.assertTrue(fl);
    }
}
