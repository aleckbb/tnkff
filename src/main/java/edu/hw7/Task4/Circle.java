package edu.hw7.Task4;

public class Circle {

    private static final Point CENTER = new Point(0.5, 0.5);
    private static final double RADIUS = 0.5;

    public boolean isPointOfCircle(Point point) {
        return (point.getX() - CENTER.getX()) * (point.getX() - CENTER.getX())
            + (point.getY() - CENTER.getY()) * (point.getY() - CENTER.getY()) <= Math.pow(RADIUS, 2);
    }
}
