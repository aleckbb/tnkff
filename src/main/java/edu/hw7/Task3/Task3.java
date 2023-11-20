package edu.hw7.Task3;

public class Task3 {

    private Task3() {

    }

    public static void main(String[] args) {
        initDataBase();
        new Thread(() -> PersonDatabaseImpl.findByName("ssss")).start();
        new Thread(() -> PersonDatabaseImpl.findByAddress("ssss")).start();
        new Thread(() -> PersonDatabaseImpl.findByPhone("ssss")).start();
    }

    public static void initDataBase(){
        PersonDatabaseImpl hui = new PersonDatabaseImpl();
        hui.add(new Person(1, "Алексей", "бурури", "12345"));
        hui.add(new Person(2, "Александр", "буру", "22345"));
        hui.add(new Person(3, "Дмитрий", "бу", "32345"));
    }
}
