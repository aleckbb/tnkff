package edu.hw8.Task3;

import java.util.HashMap;
import java.util.Map;

public class Maps {
    public static volatile Map<String, String> passwordsFromDatabase = new HashMap<>();
    public static Map<String, String> passwordsOfClients = new HashMap<>();

    private Maps() {

    }
}
