package edu.project2;

import java.util.Random;
import java.util.Stack;

public class GenerationByRecursiveBacktrackerAlgorithm implements Generator {
    private static final int THREE = 3;
    private static final int FOUR = 4;

    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        gridInit(grid, height, width);
        Random random = new Random();
        int x1 = random.nextInt(height);
        int y1 = random.nextInt(width);
        Cell currentCell = grid[x1][y1];
        Stack<Cell> cellStack = new Stack<>();
        cellStack.add(currentCell);
        currentCell.setVisited(true);
        while (!cellStack.isEmpty()) {
            switch (direction(currentCell, grid, height, width)) {
                case 1 -> {
                    currentCell = grid[currentCell.getRow() - 1][currentCell.getCol()];
                    currentCell.setWallBottom(false);
                    cellStack.add(currentCell);
                    currentCell.setVisited(true);
                }
                case 2 -> {
                    currentCell.setWallBottom(false);
                    currentCell = grid[currentCell.getRow() + 1][currentCell.getCol()];
                    cellStack.add(currentCell);
                    currentCell.setVisited(true);
                }
                case THREE -> {
                    currentCell = grid[currentCell.getRow()][currentCell.getCol() - 1];
                    currentCell.setWallRight(false);
                    cellStack.add(currentCell);
                    currentCell.setVisited(true);
                }
                case FOUR -> {
                    currentCell.setWallRight(false);
                    currentCell = grid[currentCell.getRow()][currentCell.getCol() + 1];
                    cellStack.add(currentCell);
                    currentCell.setVisited(true);
                }
                default -> {
                    cellStack.pop();
                    if (!cellStack.isEmpty()) {
                        currentCell = cellStack.peek();
                    }
                }
            }
        }
        for (int x = 0; x < height; ++x) {
            for (int y = 0; y < width; ++y) {
                grid[x][y].setVisited(false);
            }
        }
        return new Maze(height, width, grid);
    }

    public void gridInit(Cell[][] grid, int height, int width){
        for (int x = 0; x < height; ++x) {
            for (int y = 0; y < width; ++y) {
                grid[x][y] = new Cell(x, y);
                grid[x][y].setWallRight(true);
                grid[x][y].setWallBottom(true);
            }
        }
    }

    public int direction(Cell currentCell, Cell[][] grid, int height, int width) {
        boolean upDirection = isUpDirection(currentCell, grid);
        boolean downDirection = isDownDirection(currentCell, grid, height);
        boolean leftDirection = isLeftDirection(currentCell, grid);
        boolean rightDirection = isRightDirection(currentCell, grid, width);

        int choice = 0;
        Random random = new Random();
        while (choice == 0 && (upDirection || downDirection || leftDirection || rightDirection)) {
            switch (random.nextInt(FOUR) + 1) {
                case 1 -> {
                    if (upDirection) {
                        choice = 1;
                    }
                }
                case 2 -> {
                    if (downDirection) {
                        choice = 2;
                    }
                }
                case THREE -> {
                    if (leftDirection) {
                        choice = THREE;
                    }
                }
                default -> {
                    if (rightDirection) {
                        choice = FOUR;
                    }
                }
            }
        }
        return choice;
    }

    public boolean isUpDirection(Cell currentCell, Cell[][] grid) {
        return currentCell.getRow() != 0
            && !grid[currentCell.getRow() - 1][currentCell.getCol()].getVisited();
    }

    public boolean isDownDirection(Cell currentCell, Cell[][] grid, int height) {
        return currentCell.getRow() != height - 1
            && !grid[currentCell.getRow() + 1][currentCell.getCol()].getVisited();
    }

    public boolean isLeftDirection(Cell currentCell, Cell[][] grid) {
        return currentCell.getCol() != 0
            && !grid[currentCell.getRow()][currentCell.getCol() - 1].getVisited();
    }

    public boolean isRightDirection(Cell currentCell, Cell[][] grid, int width) {
        return currentCell.getCol() != width - 1
            && !grid[currentCell.getRow()][currentCell.getCol() + 1].getVisited();
    }
}
