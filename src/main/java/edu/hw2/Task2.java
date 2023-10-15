package edu.hw2;

public class Task2 {
    public static class Rectangle {
        private int width;
        private int height;

        public Rectangle() {

        }

        public Rectangle(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public Rectangle setWidth(int width) {
            this.width = width;
            return this;
        }

        public Rectangle setHeight(int height) {
            this.height = height;
            return this;
        }

        double area() {
            return width * height;
        }
    }

    public static class Square extends Rectangle {

        public Square() {

        }

        public Square(int side) {
            super(side, side);
        }

        public Square setSide(int side) {
            return new Square(side);
        }
    }
}
