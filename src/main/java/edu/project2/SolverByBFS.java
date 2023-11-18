package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SolverByBFS implements Solver {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int THREE = 3;
    private static final int FOUR = 4;

    @Override
    public List<Cell> solve(Maze maze, Coordinate start, Coordinate end) {
        List<Cell> path = new ArrayList<>();
        if (!ValidationUtility.isValidationMaze(maze)
            || !ValidationUtility.isValidationCoordinate(maze, start)
            || !ValidationUtility.isValidationCoordinate(maze, end)) {
            return path;
        }
        resetVisited(maze);
        Stack<Cell> cellStack = new Stack<>();
        Cell currentCell = maze.getGrid()[start.row()][start.col()];
        cellStack.add(currentCell);

        maze.getGrid()[start.row()][start.col()].setVisited(true);

        while (!cellStack.isEmpty()) {
            if (currentCell.getRow() == end.row() && currentCell.getCol() == end.col()) {
                path.addFirst(cellStack.pop());
            } else {
                switch (direction(currentCell, maze)) {
                    case ONE -> {
                        currentCell = maze.getGrid()[currentCell.getRow() - 1][currentCell.getCol()];
                        cellStack.add(currentCell);
                        currentCell.setVisited(true);
                    }
                    case TWO -> {
                        currentCell = maze.getGrid()[currentCell.getRow() + 1][currentCell.getCol()];
                        cellStack.add(currentCell);
                        currentCell.setVisited(true);
                    }
                    case THREE -> {
                        currentCell = maze.getGrid()[currentCell.getRow()][currentCell.getCol() - 1];
                        cellStack.add(currentCell);
                        currentCell.setVisited(true);
                    }
                    case FOUR -> {
                        currentCell = maze.getGrid()[currentCell.getRow()][currentCell.getCol() + 1];
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
        }
        return path;
    }

    public int direction(Cell currentCell, Maze maze) {
        boolean upDirection = isUpDirection(currentCell, maze);
        boolean downDirection = isDownDirection(currentCell, maze);
        boolean leftDirection = isLeftDirection(currentCell, maze);
        boolean rightDirection = isRightDirection(currentCell, maze);

        int choice = 0;
        if (upDirection) {
            choice = ONE;
        } else if (downDirection) {
            choice = TWO;
        } else if (leftDirection) {
            choice = THREE;
        } else if (rightDirection) {
            choice = FOUR;
        }
        return choice;
    }

    public boolean isUpDirection(Cell currentCell, Maze maze) {
        return currentCell.getRow() != 0
            && !maze.getGrid()[currentCell.getRow() - 1][currentCell.getCol()].getWallBottom()
            && !maze.getGrid()[currentCell.getRow() - 1][currentCell.getCol()].getVisited();
    }

    public boolean isDownDirection(Cell currentCell, Maze maze) {
        return currentCell.getRow() != maze.getHeight() - 1
            && !maze.getGrid()[currentCell.getRow()][currentCell.getCol()].getWallBottom()
            && !maze.getGrid()[currentCell.getRow() + 1][currentCell.getCol()].getVisited();
    }

    public boolean isLeftDirection(Cell currentCell, Maze maze) {
        return currentCell.getCol() != 0
            && !maze.getGrid()[currentCell.getRow()][currentCell.getCol() - 1].getWallRight()
            && !maze.getGrid()[currentCell.getRow()][currentCell.getCol() - 1].getVisited();
    }

    public boolean isRightDirection(Cell currentCell, Maze maze) {
        return currentCell.getCol() != maze.getWidth() - 1
            && !maze.getGrid()[currentCell.getRow()][currentCell.getCol()].getWallRight()
            && !maze.getGrid()[currentCell.getRow()][currentCell.getCol() + 1].getVisited();
    }

    public void resetVisited(Maze maze) {
        for (int x = 0; x < maze.getHeight(); ++x) {
            for (int y = 0; y < maze.getWidth(); ++y) {
                maze.getGrid()[x][y].setVisited(false);
            }
        }
    }
}
