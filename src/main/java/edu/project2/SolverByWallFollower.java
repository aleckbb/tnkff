package edu.project2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class SolverByWallFollower implements Solver { // по левой руке
    private static final int THREE = 3;
    private static final int FOUR = 4;

    @Override
    public List<Cell> solve(Maze maze, Coordinate start, Coordinate end) {
        List<Cell> path = new ArrayList<>();
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

        int choice = 0;
        switch (prevDirection) {
            case -1 -> {
                if (upDirection) {
                    choice = 1;
                } else if (downDirection) {
                    choice = 2;
                } else if (leftDirection) {
                    choice = THREE;
                } else if (rightDirection) {
                    choice = FOUR;
                }
            }
            case 1 -> {
                if (leftDirection) {
                    choice = THREE;
                } else if (upDirection) {
                    choice = 1;
                } else if (rightDirection) {
                    choice = FOUR;
                } else if (downDirection) {
                    choice = 2;
                }
            }
            case 2 -> {
                if (rightDirection) {
                    choice = FOUR;
                } else if (downDirection) {
                    choice = 2;
                } else if (leftDirection) {
                    choice = THREE;
                } else if (upDirection) {
                    choice = 1;
                }
            }
            case 3 -> {
                if (downDirection) {
                    choice = 2;
                } else if (leftDirection) {
                    choice = THREE;
                } else if (upDirection) {
                    choice = 1;
                } else if (rightDirection) {
                    choice = FOUR;
                }
            }
            case 4 -> {
                if (upDirection) {
                    choice = 1;
                } else if (rightDirection) {
                    choice = FOUR;
                } else if (downDirection) {
                    choice = 2;
                } else if (leftDirection) {
                    choice = THREE;
                }
            }
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
}
