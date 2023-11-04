package edu.project2;

import java.util.List;

@SuppressWarnings("RegexpSinglelineJava")
public class Renderer1 implements Renderer {
    private static final String DOUBLE_WALL = "[=][=]";
    private static final String ONCE_WALL = "[=]";
    private static final String PASSAGE = "   ";
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

    @Override
    public void render(Maze maze) {
        for (int j = 0; j < maze.getWidth(); ++j) {
            System.out.print(DOUBLE_WALL);
        }
        System.out.print(ONCE_WALL);
        System.out.print("\n");
        for (int i = 0; i < maze.getHeight(); ++i) {
            System.out.print(ONCE_WALL);
            for (int j = 0; j < maze.getWidth(); ++j) {
                System.out.print(PASSAGE);
                System.out.print(maze.getGrid()[i][j].getWallRight() ? ONCE_WALL : PASSAGE);
            }
            System.out.print("\n");
            System.out.print(ONCE_WALL);
            for (int j = 0; j < maze.getWidth(); ++j) {
                System.out.print(maze.getGrid()[i][j].getWallBottom() ? DOUBLE_WALL : PASSAGE + ONCE_WALL);
            }
            System.out.print("\n");
        }
    }

    @Override
    public void render(Maze maze, List<Cell> path) {
        for (int j = 0; j < maze.getWidth(); ++j) {
            System.out.print(DOUBLE_WALL);
        }
        System.out.print(ONCE_WALL);
        System.out.print("\n");
        for (int i = 0; i < maze.getHeight(); ++i) {
            System.out.print(ONCE_WALL);
            for (int j = 0; j < maze.getWidth(); ++j) {
                if (maze.getGrid()[i][j].equals(path.get(0))) {
                    System.out.print(
                        path.contains(maze.getGrid()[i][j]) ? ANSI_GREEN_BACKGROUND + " S " + ANSI_RESET : PASSAGE);
                } else if (maze.getGrid()[i][j].equals(path.get(path.size() - 1))) {
                    System.out.print(
                        path.contains(maze.getGrid()[i][j]) ? ANSI_RED_BACKGROUND + " E " + ANSI_RESET : PASSAGE);
                } else {
                    System.out.print(path.contains(maze.getGrid()[i][j]) ? " ▪ " : PASSAGE);
                }
                System.out.print(maze.getGrid()[i][j].getWallRight() ? ONCE_WALL : PASSAGE);
            }
            System.out.print("\n");
            System.out.print(ONCE_WALL);
            for (int j = 0; j < maze.getWidth(); ++j) {
                System.out.print(maze.getGrid()[i][j].getWallBottom() ? DOUBLE_WALL : PASSAGE + ONCE_WALL);
            }
            System.out.print("\n");
        }
    }
}
