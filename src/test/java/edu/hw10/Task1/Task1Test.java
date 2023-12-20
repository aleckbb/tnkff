package edu.hw10.Task1;

import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task1Test {
    @Test
    @DisplayName("Программа справляется с задачей")
    void test1()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // given
        RandomObjectGenerator rog = new RandomObjectGenerator();

        // when
        MyClass myClass = (MyClass) rog.nextObject(MyClass.class, "create");
        MyRecord myRecord = (MyRecord) rog.nextObject(MyRecord.class);

        // then
        assertNotNull(myClass);
        assertNotNull(myRecord);
    }

    @Test
    @DisplayName("nextObject возвращает null, если метод не найден")
    void test2()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // given
        RandomObjectGenerator rog = new RandomObjectGenerator();

        // when
        MyClass myClass = (MyClass) rog.nextObject(MyClass.class, "с1");

        // then
        assertNull(myClass);
    }

    @Test
    @DisplayName("nextObject возвращает объект с полями по умолчанию, если конструктор не принимает параметры")
    void test3()
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        // given
        RandomObjectGenerator rog = new RandomObjectGenerator();

        // when
        MyClass myClass = (MyClass) rog.nextObject(MyClass.class);

        // then
        assertNotNull(myClass);
        assertNull(myClass.string);
        assertNull(myClass.is);
        assertEquals(myClass.x, 0);
    }
}
