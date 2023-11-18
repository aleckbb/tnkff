package edu.hw6.Task1;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jetbrains.annotations.NotNull;

public class DiskMap extends AbstractMap<String, String> implements Map<String, String> {
    public static final String PATH = "src/main/java/edu/hw6/Task1/Map.txt";

    public DiskMap() {
        this.clear();
    }

    public void write(Set<Entry<String, String>> entrySet) {
        try (var oos = new ObjectOutputStream(new FileOutputStream(PATH))) {
            for (var entry : entrySet) {
                oos.writeObject(entry);
            }
        } catch (IOException | NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        Set<Entry<String, String>> entrySet = new HashSet<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PATH))) {
            boolean isReadable = true;
            while (isReadable) {
                try {
                    AbstractMap.SimpleEntry<String, String> entry =
                        (AbstractMap.SimpleEntry<String, String>) ois.readObject();
                    entrySet.add(entry);
                } catch (EOFException | ClassNotFoundException e) {
                    isReadable = false;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return entrySet;
    }

    public String remove(String key) {
        var entrySet = entrySet();
        String value = null;
        for (var entry : entrySet) {
            if (entry.getKey().equals(key)) {
                value = entry.getValue();
                if (value != null) {
                    entrySet.remove(entry);
                    write(entrySet);
                    return value;
                }
            }
        }
        return value;
    }

    public String put(String key, String value) {
        var entrySet = entrySet();
        AbstractMap.SimpleEntry<String, String> newEntry = new SimpleEntry<>(key, value);
        for (var entry : entrySet) {
            if (entry.getKey().equals(key)) {
                entrySet.remove(entry);
                entrySet.add(newEntry);
            }
        }
        if (!entrySet.contains(newEntry)) {
            entrySet.add(newEntry);
        }
        write(entrySet);
        return value;
    }

    public void clear() {
        write(new HashSet<>());
    }
}
