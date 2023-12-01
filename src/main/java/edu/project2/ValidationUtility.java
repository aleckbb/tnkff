package edu.project2;

public final class ValidationUtility {

    private ValidationUtility() {

    }

    static boolean isValidationCoordinate(Maze maze, Coordinate coordinate) {
        return coordinate.row() >= 0
            && coordinate.row() <= maze.getHeight() - 1
            && coordinate.col() >= 0
            && coordinate.col() <= maze.getWidth() - 1;
    }

    static boolean isValidationMaze(Maze maze) {
        if (maze == null || maze.getGrid() == null) {
            return false;
        }
        for (int x = 0; x < maze.getHeight(); ++x) {
            for (int y = 0; y < maze.getWidth(); ++y) {
                if (maze.getGrid()[x][y] == null) {
                    return false;
                }
            }
        }
        return maze.getHeight() > 0
            && maze.getWidth() > 0
            && maze.getGrid().length > 0;
    }

    static boolean isValidationSize(int height, int width) {
        return height > 0
            && width > 0;
    }
}
