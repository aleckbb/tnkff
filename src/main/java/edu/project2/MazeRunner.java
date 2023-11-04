package edu.project2;

import java.util.List;

@SuppressWarnings("UncommentedMain")
public class MazeRunner {
    private MazeRunner() {
    }

    public static void main(String[] args) {
        GenerationByEllerAlgorithm maze1 = new GenerationByEllerAlgorithm();
        Maze newMaze1 = maze1.generate(10, 10);
        GenerationByRecursiveBacktrackerAlgorithm maze2 = new GenerationByRecursiveBacktrackerAlgorithm();
        Maze newMaze2 = maze2.generate(10, 10);
        Renderer1 renderer1 = new Renderer1();
        //renderer1.render(newMaze1);
        //renderer1.render(newMaze2);
        SolverByBFS solverBFS = new SolverByBFS();
        SolverByWallFollower solverByWallFollower = new SolverByWallFollower();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(3, 9);

        //List<Cell> path = solverBFS.solve(newMaze1, start, end);
        //renderer1.render(newMaze1, path);

        //path = solverBFS.solve(newMaze2, start, end);
        //renderer1.render(newMaze2, path);

        List<Cell> path = solverByWallFollower.solve(newMaze1, start, end);
        renderer1.render(newMaze1, path);

        path = solverByWallFollower.solve(newMaze2, start, end);
        renderer1.render(newMaze2, path);
    }
}
