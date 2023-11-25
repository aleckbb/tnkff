package edu.hw7.Task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task35Test {
    private static final PersonDatabaseReadWriteLock PERSON_DATABASE = new PersonDatabaseReadWriteLock();

    private final Thread thread1 = new Thread(() -> {
        PERSON_DATABASE.add(new Person(0, "Alexei", "Belozerov", "12345"));
        PERSON_DATABASE.add(new Person(1, "Dima", "Dolzhnikov", "54321"));
        PERSON_DATABASE.add(new Person(2, null, "D", "5"));
    });

    private final Thread thread2 = new Thread(() -> {
        PERSON_DATABASE.add(new Person(3, "Sasha", "Gonnyih", "67890"));
        PERSON_DATABASE.add(new Person(4, "Vera", "Em", "09876"));
        PERSON_DATABASE.add(new Person(5, "V", null, "0"));
    });

    @BeforeEach
    void setUp(){
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Все записи добавляются")
    void test1(){
        // Then
        assertEquals(6, PERSON_DATABASE.size());
    }

    @Test
    @DisplayName("Запись не найдется если у неё есть не все атрибуты")
    void test2(){
        // Then
        assertNull(PERSON_DATABASE.findByName("V"));
    }

    @Test
    @DisplayName("При удалении запись действительно удаляется")
    void test3(){
        // When
        PERSON_DATABASE.delete(0);

        // Then
        assertEquals(5, PERSON_DATABASE.size());
    }

    @Test
    @DisplayName("При удалении несуществующей записи ничего не происходит")
    void test4(){
        // When
        PERSON_DATABASE.delete(6);

        // Then
        assertEquals(6, PERSON_DATABASE.size());
    }

    @Test
    @DisplayName("При многопоточном удалении все удаляемые записи удаляются")
    void test5(){
        // Given
        Thread thread1 = new Thread(() -> {
            PERSON_DATABASE.delete(0);
            PERSON_DATABASE.delete(1);
            PERSON_DATABASE.delete(2);
        });
        Thread thread2 = new Thread(() -> {
            PERSON_DATABASE.delete(3);
            PERSON_DATABASE.delete(4);
            PERSON_DATABASE.delete(5);
        });

        // When
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Then
        assertEquals(0, PERSON_DATABASE.size());
    }

    @Test
    @DisplayName("Запись найдется если у неё есть все атрибуты")
    void test6(){
        // Then
        assertNotNull(PERSON_DATABASE.findByName("Vera"));
    }
}
