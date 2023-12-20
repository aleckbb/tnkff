package edu.hw10.Task1;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.NotNull;

public class MyClass {

    public int x;
    public String string;
    public Boolean is;

    //public MyClass() {

    //}

    public MyClass create(@Max(1000) int x, @NotNull String string, @NotNull Boolean is) {
        this.x = x;
        this.string = string;
        this.is = is;
        return this;
    }
}
