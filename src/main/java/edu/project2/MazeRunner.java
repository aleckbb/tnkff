package edu.project2;

public class MazeRunner {
    public static void main(String[] args) {
        GenerationByEulerAlgorithm maze = new GenerationByEulerAlgorithm();
        Maze newMaze = maze.generate(10, 10);
        Renderer1 renderer1 = new Renderer1();
        renderer1.render(newMaze);
    }
}
