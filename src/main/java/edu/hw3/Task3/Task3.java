package edu.hw3.Task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task3 {
    private static final Logger LOGGER = LogManager.getLogger();

    private Task3() {
    }

    public static HashMap freqDict(ArrayList<Object> arrayList) {
        HashMap<Object, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arrayList.size(); ++i) {
            var tmp = arrayList.get(i);
            if (hashMap.get(tmp) == null) {
                hashMap.put(tmp, 1);
            } else {
                hashMap.put(tmp, hashMap.get(tmp) + 1);
            }
        }
        return hashMap;
    }

    public static void main(String[] args) {
        ArrayList<Object> arrayList = new ArrayList<>(Arrays.asList(1, 1, 2, 2));
        LOGGER.info(freqDict(arrayList));
    }
}
