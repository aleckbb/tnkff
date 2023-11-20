package edu.hw7.Task4;

public class Circle {

    private static final Point CENTER = new Point(0.5, 0.5);
    private static final double RADIUS = 0.5;

    public boolean isPointOfCircle(Point point) {
        return Math.pow(Math.abs(point.getX()) - CENTER.getX(), 2)
            + Math.pow(Math.abs(point.getY()) - CENTER.getY(), 2) <= Math.pow(RADIUS, 2);
    }
}
