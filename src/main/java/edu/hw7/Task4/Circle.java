package edu.hw7.Task4;

public class Circle {

    private static final Point center = new Point(0.5, 0.5);
    private static final double radius = 0.5;

    public boolean isPointOfCircle(Point point){
        return Math.pow(Math.abs(point.getX()) - center.getX(), 2)
                + Math.pow(Math.abs(point.getY()) - center.getY(), 2) <= Math.pow(radius, 2);
    }
}
