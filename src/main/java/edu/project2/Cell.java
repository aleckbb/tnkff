package edu.project2;

import java.util.Objects;

public final class Cell {
    private final int row;
    private final int col;
    private boolean wallRight = false;
    private boolean wallBottom = false;
    private int set;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean getWallRight() {
        return wallRight;
    }

    public boolean getWallBottom() {
        return wallBottom;
    }

    public int getSet() {
        return set;
    }

    public void setWallRight(boolean wallRight) {
        this.wallRight = wallRight;
    }

    public void setWallBottom(boolean wallBottom) {
        this.wallBottom = wallBottom;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public void copyCell(Cell cell) {
        this.set = cell.set;
        this.wallRight = cell.wallRight;
        this.wallBottom = cell.wallBottom;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (Cell) obj;
        return this.row == that.row &&
            this.col == that.col &&
            this.wallRight == that.wallRight &&
            this.wallBottom == that.wallBottom &&
            this.set == that.set;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, wallRight, wallBottom, set);
    }

    @Override
    public String toString() {
        return "Cell[" +
            "row=" + row + ", " +
            "col=" + col + ", " +
            "wallRight=" + wallRight + ", " +
            "wallBottom=" + wallBottom + ", " +
            "set=" + set + ']';
    }

}
