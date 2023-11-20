package edu.hw7.Task4;

public class Task4 {

    private Task4() {

    }

    public static void main(String[] args) {
        System.out.println(oneThreadPI(10000000));
    }

    public static double oneThreadPI(int numberOfIterations) {
        int totalCount = 0;
        int circleCount = 0;
        Circle circle = new Circle();
        for (int i = 1; i <= numberOfIterations; ++i) {
            var point = new Point();
            if (circle.isPointOfCircle(point)) {
                circleCount++;
            }
            totalCount++;
        }
        return (double) (4 * circleCount) / totalCount;
    }
}
