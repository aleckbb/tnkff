package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@SuppressWarnings("InnerAssignment")
public class SolverByWallFollower implements Solver { // по левой руке
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
        int prevDirection = -1;
        while (!cellStack.isEmpty()) {
            if (currentCell.getRow() == end.row() && currentCell.getCol() == end.col()) {
                path.addFirst(cellStack.pop());
            } else {
                switch (direction(currentCell, maze, prevDirection)) {
                    case 1 -> {
                        currentCell = maze.getGrid()[currentCell.getRow() - 1][currentCell.getCol()];
                        cellStack.add(currentCell);
                        currentCell.setVisited(true);
                        prevDirection = 1;
                    }
                    case 2 -> {
                        currentCell = maze.getGrid()[currentCell.getRow() + 1][currentCell.getCol()];
                        cellStack.add(currentCell);
                        currentCell.setVisited(true);
                        prevDirection = 2;
                    }
                    case THREE -> {
                        currentCell = maze.getGrid()[currentCell.getRow()][currentCell.getCol() - 1];
                        cellStack.add(currentCell);
                        currentCell.setVisited(true);
                        prevDirection = THREE;
                    }
                    case FOUR -> {
                        currentCell = maze.getGrid()[currentCell.getRow()][currentCell.getCol() + 1];
                        cellStack.add(currentCell);
                        currentCell.setVisited(true);
                        prevDirection = FOUR;
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

    public int direction(Cell currentCell, Maze maze, int prevDirection) {
        boolean upDirection = isUpDirection(currentCell, maze);
        boolean downDirection = isDownDirection(currentCell, maze);
        boolean leftDirection = isLeftDirection(currentCell, maze);
        boolean rightDirection = isRightDirection(currentCell, maze);

        int choice;
        switch (prevDirection) {
            case -1 -> choice = firstDirection(upDirection, downDirection, leftDirection, rightDirection);
            case 1 -> choice = nowUpDirection(upDirection, downDirection, leftDirection, rightDirection);
            case 2 -> choice = nowDownDirection(upDirection, downDirection, leftDirection, rightDirection);
            case THREE -> choice = nowLeftDirection(upDirection, downDirection, leftDirection, rightDirection);
            default -> choice = nowRightDirection(upDirection, downDirection, leftDirection, rightDirection);
        }

        return choice;
    }

    public int firstDirection(
        boolean upDirection,
        boolean downDirection,
        boolean leftDirection,
        boolean rightDirection
    ) {
        int choice = 0;
        if (upDirection) {
            choice = 1;
        } else if (downDirection) {
            choice = 2;
        } else if (leftDirection) {
            choice = THREE;
        } else if (rightDirection) {
            choice = FOUR;
        }
        return choice;
    }

    public int nowUpDirection(
        boolean upDirection,
        boolean downDirection,
        boolean leftDirection,
        boolean rightDirection
    ) {
        int choice = 0;
        if (leftDirection) {
            choice = THREE;
        } else if (upDirection) {
            choice = 1;
        } else if (rightDirection) {
            choice = FOUR;
        } else if (downDirection) {
            choice = 2;
        }
        return choice;
    }

    public int nowDownDirection(
        boolean upDirection,
        boolean downDirection,
        boolean leftDirection,
        boolean rightDirection
    ) {
        int choice = 0;
        if (rightDirection) {
            choice = FOUR;
        } else if (downDirection) {
            choice = 2;
        } else if (leftDirection) {
            choice = THREE;
        } else if (upDirection) {
            choice = 1;
        }
        return choice;
    }

    public int nowLeftDirection(
        boolean upDirection,
        boolean downDirection,
        boolean leftDirection,
        boolean rightDirection
    ) {
        int choice = 0;
        if (downDirection) {
            choice = 2;
        } else if (leftDirection) {
            choice = THREE;
        } else if (upDirection) {
            choice = 1;
        } else if (rightDirection) {
            choice = FOUR;
        }
        return choice;
    }

    public int nowRightDirection(
        boolean upDirection,
        boolean downDirection,
        boolean leftDirection,
        boolean rightDirection
    ) {
        int choice = 0;
        if (upDirection) {
            choice = 1;
        } else if (rightDirection) {
            choice = FOUR;
        } else if (downDirection) {
            choice = 2;
        } else if (leftDirection) {
            choice = THREE;
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
