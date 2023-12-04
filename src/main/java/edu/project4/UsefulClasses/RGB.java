package edu.project4.UsefulClasses;

import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("MagicNumber")
public class RGB {
    public static final RGB RED = new RGB(123, 0, 0);
    public static final RGB GREEN = new RGB(0, 255, 0);
    public static final RGB BLUE = new RGB(0, 0, 255);
    public static final RGB VIOLET = new RGB(238, 130, 238);
    public static final RGB PINK = new RGB(125, 86, 103);
    public static final RGB BLACK = new RGB(0, 0, 0);

    private int red;
    private int green;
    private int blue;

    public RGB() {
        red = ThreadLocalRandom.current().nextInt(256);
        green = ThreadLocalRandom.current().nextInt(256);
        blue = ThreadLocalRandom.current().nextInt(256);
    }

    public RGB(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRGB() {
        return (red << 16 | green << 8 | blue);
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }
}
