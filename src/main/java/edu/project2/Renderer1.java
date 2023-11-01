package edu.project2;

import java.util.List;

public class Renderer1 implements Renderer {
    @Override
    public String render(Maze maze) {
        for (int j = 0; j < maze.getWidth(); ++j) {
            System.out.print("[=][=]");
        }
        System.out.print("[=]");
        System.out.print("\n");
        for (int i = 0; i < maze.getHeight(); ++i) {
            System.out.print("[=]");
            for (int j = 0; j < maze.getWidth(); ++j) {
                System.out.print("   ");
                System.out.print(maze.getGrid()[i][j].getWallRight() ? "[=]" : "   ");
            }
            System.out.print("\n");
            System.out.print("[=]");
            for (int j = 0; j < maze.getWidth(); ++j) {
                System.out.print(maze.getGrid()[i][j].getWallBottom() ? "[=][=]" : "   [=]");
            }
            System.out.print("\n");
        }
        return "";
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        return null;
    }
}
