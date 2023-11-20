package edu.hw7.Task4;

import java.security.SecureRandom;

public class Point {

    private double x;
    private double y;

    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Point(){
        getRandomPoint();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void getRandomPoint(){
        SecureRandom secureRandom = new SecureRandom();
        x = secureRandom.nextDouble();
        y = secureRandom.nextDouble();
    }
}
