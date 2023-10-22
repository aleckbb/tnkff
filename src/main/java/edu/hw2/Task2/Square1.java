package edu.hw2.Task2;

public class Square1 extends Rectangle1 {
    public static class Square extends Rectangle {
        public Square() {

        }

        public Square(int side) {
            super(side, side);
        }

        public void setSide(int side) {
            setHeight(side);
            setWidth(side);
        }
    }
}
