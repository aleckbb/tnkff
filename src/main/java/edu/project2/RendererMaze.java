package edu.project2;

import java.util.List;

public class RendererMaze implements Renderer {
    private static final String DOUBLE_WALL = "[=][=]";
    private static final String ONCE_WALL = "[=]";
    private static final String PASSAGE = "   ";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    private static final int MIN_PATH_LENGTH = 2;

    @Override
    public String render(Maze maze) {
        resetVisited(maze);
        StringBuilder mazeInString = new StringBuilder();
        for (int j = 0; j < maze.getWidth(); ++j) {
            mazeInString.append(DOUBLE_WALL);
        }
        mazeInString.append(ONCE_WALL);
        mazeInString.append("\n");
        for (int i = 0; i < maze.getHeight(); ++i) {
            mazeInString.append(ONCE_WALL);
            for (int j = 0; j < maze.getWidth(); ++j) {
                mazeInString.append(PASSAGE);
                mazeInString.append(maze.getGrid()[i][j].getWallRight() ? ONCE_WALL : PASSAGE);
            }
            mazeInString.append("\n");
            mazeInString.append(ONCE_WALL);
            for (int j = 0; j < maze.getWidth(); ++j) {
                mazeInString.append(maze.getGrid()[i][j].getWallBottom() ? DOUBLE_WALL : PASSAGE + ONCE_WALL);
            }
            mazeInString.append("\n");
        }
        return mazeInString.toString();
    }

    @Override
    public String render(Maze maze, List<Cell> path) {
        resetVisited(maze);
        StringBuilder mazeInString = new StringBuilder();
        if (path.size() < MIN_PATH_LENGTH) {
            return "Введены некорректные данные";
        }
        for (int j = 0; j < maze.getWidth(); ++j) {
            mazeInString.append(DOUBLE_WALL);
        }
        mazeInString.append(ONCE_WALL);
        mazeInString.append("\n");
        for (int i = 0; i < maze.getHeight(); ++i) {
            mazeInString.append(ONCE_WALL);
            for (int j = 0; j < maze.getWidth(); ++j) {
                if (maze.getGrid()[i][j].equals(path.get(0))) {
                    mazeInString.append(
                        path.contains(maze.getGrid()[i][j]) ? ANSI_GREEN_BACKGROUND + " S " + ANSI_RESET : PASSAGE);
                } else if (maze.getGrid()[i][j].equals(path.get(path.size() - 1))) {
                    mazeInString.append(
                        path.contains(maze.getGrid()[i][j]) ? ANSI_RED_BACKGROUND + " E " + ANSI_RESET : PASSAGE);
                } else {
                    mazeInString.append(path.contains(maze.getGrid()[i][j]) ? " ▪ " : PASSAGE);
                }
                mazeInString.append(maze.getGrid()[i][j].getWallRight() ? ONCE_WALL : PASSAGE);
            }
            mazeInString.append("\n");
            mazeInString.append(ONCE_WALL);
            for (int j = 0; j < maze.getWidth(); ++j) {
                mazeInString.append(maze.getGrid()[i][j].getWallBottom() ? DOUBLE_WALL : PASSAGE + ONCE_WALL);
            }
            mazeInString.append("\n");
        }
        return mazeInString.toString();
    }

    public void resetVisited(Maze maze) {
        for (int x = 0; x < maze.getHeight(); ++x) {
            for (int y = 0; y < maze.getWidth(); ++y) {
                maze.getGrid()[x][y].setVisited(false);
            }
        }
    }
}
