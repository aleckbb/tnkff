package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.RecursiveTask;

public class MultiThreadSolverByBFS extends RecursiveTask<List<Cell>> implements Solver {
    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 4;

    private final Stack<Cell> cellStack;
    private final Maze maze;
    private final Coordinate start;
    private final Coordinate end;

    MultiThreadSolverByBFS(Stack<Cell> cellStack, Maze maze, Coordinate start, Coordinate end) {
        this.cellStack = new Stack<>();
        this.cellStack.addAll(cellStack);
        this.maze = maze;
        this.start = start;
        this.end = end;
    }

    @Override
    protected List<Cell> compute() {
        return solve(maze, start, end);
    }

    @Override
    public List<Cell> solve(Maze maze, Coordinate start, Coordinate end) {
        List<Cell> path = new ArrayList<>();
        if (!ValidationUtility.isValidationMaze(maze)
            || !ValidationUtility.isValidationCoordinate(maze, start)
            || !ValidationUtility.isValidationCoordinate(maze, end)) {
            return path;
        }
        Cell currentCell = maze.getGrid()[start.row()][start.col()];
        cellStack.add(currentCell);

        maze.getGrid()[start.row()][start.col()].setVisited(true);

        while (!cellStack.isEmpty()) {
            if (currentCell.getRow() == end.row() && currentCell.getCol() == end.col()) {
                path.addFirst(cellStack.pop());
            } else {
                List<Integer> directions = directions(currentCell, maze);
                Cell tmp = new Cell(
                    currentCell.getRow(),
                    currentCell.getCol(),
                    currentCell.getWallRight(),
                    currentCell.getWallBottom()
                );
                for (Integer direction : directions) {
                    switch (direction) {
                        case UP -> {
                            currentCell = maze.getGrid()[tmp.getRow() - 1][tmp.getCol()];
                            actionsForDifferentCountOfDirections(directions, currentCell, path);
                        }
                        case DOWN -> {
                            currentCell = maze.getGrid()[tmp.getRow() + 1][tmp.getCol()];
                            actionsForDifferentCountOfDirections(directions, currentCell, path);
                        }
                        case LEFT -> {
                            currentCell = maze.getGrid()[tmp.getRow()][tmp.getCol() - 1];
                            actionsForDifferentCountOfDirections(directions, currentCell, path);
                        }
                        default -> {
                            currentCell = maze.getGrid()[tmp.getRow()][tmp.getCol() + 1];
                            actionsForDifferentCountOfDirections(directions, currentCell, path);
                        }
                    }
                }
                if (directions.isEmpty()) {
                    cellStack.pop();
                    if (!cellStack.isEmpty()) {
                        currentCell = cellStack.peek();
                    }
                }
            }
        }
        return path;
    }

    private void actionsForDifferentCountOfDirections(List<Integer> directions, Cell currentCell, List<Cell> path) {
        if (directions.size() <= 1) {
            cellStack.add(currentCell);
            currentCell.setVisited(true);
        } else {
            MultiThreadSolverByBFS multiThreadSolverByBFS =
                new MultiThreadSolverByBFS(
                    cellStack,
                    maze,
                    new Coordinate(currentCell.getRow(), currentCell.getCol()),
                    end
                );
            multiThreadSolverByBFS.fork();
            path.addAll(multiThreadSolverByBFS.join());
        }
    }

    private List<Integer> directions(Cell currentCell, Maze maze) {
        boolean upDirection = isUpDirection(currentCell, maze);
        boolean downDirection = isDownDirection(currentCell, maze);
        boolean leftDirection = isLeftDirection(currentCell, maze);
        boolean rightDirection = isRightDirection(currentCell, maze);

        List<Integer> directions = new ArrayList<>();
        if (upDirection) {
            directions.add(UP);
        }
        if (downDirection) {
            directions.add(DOWN);
        }
        if (leftDirection) {
            directions.add(LEFT);
        }
        if (rightDirection) {
            directions.add(RIGHT);
        }
        return directions;
    }

    private boolean isUpDirection(Cell currentCell, Maze maze) {
        return currentCell.getRow() != 0
            && !maze.getGrid()[currentCell.getRow() - 1][currentCell.getCol()].getWallBottom()
            && !maze.getGrid()[currentCell.getRow() - 1][currentCell.getCol()].getVisited();
    }

    private boolean isDownDirection(Cell currentCell, Maze maze) {
        return currentCell.getRow() != maze.getHeight() - 1
            && !maze.getGrid()[currentCell.getRow()][currentCell.getCol()].getWallBottom()
            && !maze.getGrid()[currentCell.getRow() + 1][currentCell.getCol()].getVisited();
    }

    private boolean isLeftDirection(Cell currentCell, Maze maze) {
        return currentCell.getCol() != 0
            && !maze.getGrid()[currentCell.getRow()][currentCell.getCol() - 1].getWallRight()
            && !maze.getGrid()[currentCell.getRow()][currentCell.getCol() - 1].getVisited();
    }

    private boolean isRightDirection(Cell currentCell, Maze maze) {
        return currentCell.getCol() != maze.getWidth() - 1
            && !maze.getGrid()[currentCell.getRow()][currentCell.getCol()].getWallRight()
            && !maze.getGrid()[currentCell.getRow()][currentCell.getCol() + 1].getVisited();
    }
}
