package edu.hw3.Task5;

import java.util.ArrayList;

public class Task5 {

    private Task5() {

    }

    public static Contact[] parseContacts(String[] contactsList, String order) {
        ArrayList<Contact> sortArrayList = new ArrayList<>();
        String[] person;
        if (contactsList != null && contactsList.length != 0) {
            for (String s : contactsList) {
                if (!s.isEmpty()) {
                    person = s.split(" ");
                    if (person.length == 2) {
                        sortArrayList.add(new Contact(person[0], person[1]));
                    } else if (person.length == 1) {
                        sortArrayList.add(new Contact(s, ""));
                    }
                }
            }
            if (order.equals("ASC")) {
                sortArrayList.sort(Contact::compareToASC);
            } else {
                sortArrayList.sort(Contact::compareToDESC);
            }
            return sortArrayList.toArray(new Contact[0]);
        }
        return null;
    }
}
