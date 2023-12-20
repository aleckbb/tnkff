package edu.project4.UsefulClasses;

public class Pixel {

    private RGB rgb;
    private int countHit;
    private double normal;

    public Pixel() {
        countHit = 0;
        normal = 0;
        rgb = new RGB(0, 0, 0);
    }

    public Pixel(int countHit, double normal, RGB rgb) {
        this.countHit = countHit;
        this.normal = normal;
        this.rgb = rgb;
    }

    public void incrementCountHit() {
        countHit++;
    }

    public RGB getRgb() {
        return rgb;
    }

    public int getCountHit() {
        return countHit;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }
}
