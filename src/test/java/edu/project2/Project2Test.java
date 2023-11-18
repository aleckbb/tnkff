package edu.project2;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Project2Test {

    @Test
    @DisplayName("Проверка, что лабиринт выводится корректно")
    void test1() {
        // Given
        Maze maze = getMaze();
        RendererMaze rendererMaze = new RendererMaze();

        // When
        String expected =
            "[=][=][=][=][=][=][=][=][=]\n" +
                "[=]                     [=]\n" +
                "[=]   [=][=][=][=][=]   [=]\n" +
                "[=]         [=]   [=]   [=]\n" +
                "[=][=][=][=][=]   [=]   [=]\n" +
                "[=]         [=]         [=]\n" +
                "[=]   [=][=][=]   [=][=][=]\n" +
                "[=]                     [=]\n" +
                "[=][=][=][=][=][=][=][=][=]\n";

        // Then
        assertEquals(expected, rendererMaze.render(maze));
    }

    @Test
    @DisplayName("Проверка, что лабиринт и путь в нем выводятся корректно")
    void test2() {
        // Given
        Maze maze = getMaze();
        RendererMaze rendererMaze = new RendererMaze();
        SolverByBFS solverByBFS = new SolverByBFS();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(3, 3);

        // When
        List<Cell> path = solverByBFS.solve(maze, start, end);
        String expected =
            "[=][=][=][=][=][=][=][=][=]\n" +
                "[=]\u001B[42m S \u001B[0m    ▪     ▪     ▪ [=]\n" +
                "[=]   [=][=][=][=][=]   [=]\n" +
                "[=]         [=]   [=] ▪ [=]\n" +
                "[=][=][=][=][=]   [=]   [=]\n" +
                "[=]         [=] ▪     ▪ [=]\n" +
                "[=]   [=][=][=]   [=][=][=]\n" +
                "[=]             ▪    \u001B[41m E \u001B[0m[=]\n" +
                "[=][=][=][=][=][=][=][=][=]\n";

        // Then
        assertEquals(expected, rendererMaze.render(maze, path));
    }

    @Test
    @DisplayName("Проверка, что оба способа решения выводят один результат")
    void test3() {
        // Given
        Maze maze = getMaze();
        RendererMaze rendererMaze = new RendererMaze();
        SolverByBFS solverByBFS = new SolverByBFS();
        SolverByWallFollower solverByWallFollower = new SolverByWallFollower();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(3, 3);

        // When
        List<Cell> pathByBFS = solverByBFS.solve(maze, start, end);
        List<Cell> pathByWallFollower = solverByWallFollower.solve(maze, start, end);

        // Then
        assertEquals(rendererMaze.render(maze, pathByBFS), rendererMaze.render(maze, pathByWallFollower));
    }

    @Test
    @DisplayName("Начало и конец пути - одна точка")
    void test4() {
        // Given
        Maze maze = getMaze();
        RendererMaze rendererMaze = new RendererMaze();
        SolverByBFS solverByBFS = new SolverByBFS();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(0, 0);

        // When
        List<Cell> pathByBFS = solverByBFS.solve(maze, start, end);
        String expected = "Введены некорректные данные";

        // Then
        assertEquals(expected, rendererMaze.render(maze, pathByBFS));
    }

    @Test
    @DisplayName("Точка начала пути некорректная")
    void test5() {
        // Given
        Maze maze = getMaze();
        RendererMaze rendererMaze = new RendererMaze();
        SolverByBFS solverByBFS = new SolverByBFS();
        Coordinate start = new Coordinate(-1, 0);
        Coordinate end = new Coordinate(0, 0);

        // When
        List<Cell> pathByBFS = solverByBFS.solve(maze, start, end);
        String expected = "Введены некорректные данные";

        // Then
        assertEquals(expected, rendererMaze.render(maze, pathByBFS));
    }

    @Test
    @DisplayName("Точка конца пути некорректная")
    void test6() {
        // Given
        Maze maze = getMaze();
        RendererMaze rendererMaze = new RendererMaze();
        SolverByBFS solverByBFS = new SolverByBFS();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(-1, 0);

        // When
        List<Cell> pathByBFS = solverByBFS.solve(maze, start, end);
        String expected = "Введены некорректные данные";

        // Then
        assertEquals(expected, rendererMaze.render(maze, pathByBFS));
    }

    @Test
    @DisplayName("Обе точки пути некорректные")
    void test7() {
        // Given
        Maze maze = getMaze();
        RendererMaze rendererMaze = new RendererMaze();
        SolverByBFS solverByBFS = new SolverByBFS();
        Coordinate start = new Coordinate(-1, 0);
        Coordinate end = new Coordinate(-1, 0);

        // When
        List<Cell> pathByBFS = solverByBFS.solve(maze, start, end);
        String expected = "Введены некорректные данные";

        // Then
        assertEquals(expected, rendererMaze.render(maze, pathByBFS));
    }

    @Test
    @DisplayName("Доска пустая")
    void test8() {
        // Given
        Maze maze = new Maze(4, 4, new Cell[4][4]);
        RendererMaze rendererMaze = new RendererMaze();
        SolverByBFS solverByBFS = new SolverByBFS();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(3, 3);

        // When
        List<Cell> pathByBFS = solverByBFS.solve(maze, start, end);
        String expected = "Введены некорректные данные";

        // Then
        assertEquals(expected, rendererMaze.render(maze, pathByBFS));
    }

    @Test
    @DisplayName("Доска равна null")
    void test9() {
        // Given
        Maze maze = new Maze(4, 4, null);
        RendererMaze rendererMaze = new RendererMaze();
        SolverByBFS solverByBFS = new SolverByBFS();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(3, 3);

        // When
        List<Cell> pathByBFS = solverByBFS.solve(maze, start, end);
        String expected = "Введены некорректные данные";

        // Then
        assertEquals(expected, rendererMaze.render(maze, pathByBFS));
    }

    @Test
    @DisplayName("Лабиринт равен null")
    void test10() {
        // Given
        Maze maze = null;
        RendererMaze rendererMaze = new RendererMaze();
        SolverByBFS solverByBFS = new SolverByBFS();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(3, 3);

        // When
        List<Cell> pathByBFS = solverByBFS.solve(maze, start, end);
        String expected = "Введены некорректные данные";

        // Then
        assertEquals(expected, rendererMaze.render(maze, pathByBFS));
    }

    @Test
    @DisplayName("Размеры некооректные")
    void test11() {
        // Given
        Maze maze = new Maze(-1, 10, getGrid());
        RendererMaze rendererMaze = new RendererMaze();
        SolverByBFS solverByBFS = new SolverByBFS();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(3, 3);

        // When
        List<Cell> pathByBFS = solverByBFS.solve(maze, start, end);
        String expected = "Введены некорректные данные";

        // Then
        assertEquals(expected, rendererMaze.render(maze, pathByBFS));
    }

    @Test
    @DisplayName("Массив нерешаемый, solverByWallFollower возвращает пустой список")
    void test12() {
        // Given
        Maze maze = getUnsolvableMaze();
        SolverByWallFollower solverByWallFollower = new SolverByWallFollower();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(3, 3);

        // When
        List<Cell> path = solverByWallFollower.solve(maze, start, end);

        // Then
        assertEquals(0, path.size());
    }

    @Test
    @DisplayName("Массив нерешаемый, solverByBFS возвращает пустой список")
    void test13() {
        // Given
        Maze maze = getUnsolvableMaze();
        SolverByBFS solverByBFS = new SolverByBFS();
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(3, 3);

        // When
        List<Cell> path = solverByBFS.solve(maze, start, end);;

        // Then
        assertEquals(0, path.size());
    }

    @NotNull private static Maze getMaze() {
        Cell[][] grid = new Cell[][] {
            {new Cell(0, 0, false, false),
                new Cell(0, 1, false, true),
                new Cell(0, 2, false, true),
                new Cell(0, 3, true, false)},
            {new Cell(1, 0, false, true),
                new Cell(1, 1, true, true),
                new Cell(1, 2, true, false),
                new Cell(1, 3, true, false)},
            {new Cell(2, 0, false, false),
                new Cell(2, 1, true, true),
                new Cell(2, 2, false, false),
                new Cell(2, 3, true, true)},
            {new Cell(3, 0, false, true),
                new Cell(3, 1, false, true),
                new Cell(3, 2, false, true),
                new Cell(3, 3, true, true)}
        };
        return new Maze(4, 4, grid);
    }

    @NotNull private static Cell[][] getGrid() {
        return new Cell[][] {
            {new Cell(0, 0, false, false),
                new Cell(0, 1, false, true),
                new Cell(0, 2, false, true),
                new Cell(0, 3, true, false)},
            {new Cell(1, 0, false, true),
                new Cell(1, 1, true, true),
                new Cell(1, 2, true, false),
                new Cell(1, 3, true, false)},
            {new Cell(2, 0, false, false),
                new Cell(2, 1, true, true),
                new Cell(2, 2, false, false),
                new Cell(2, 3, true, true)},
            {new Cell(3, 0, false, true),
                new Cell(3, 1, false, true),
                new Cell(3, 2, false, true),
                new Cell(3, 3, true, true)}
        };
    }

    @NotNull private static Maze getUnsolvableMaze() {
        Cell[][] grid = new Cell[][] {
            {new Cell(0, 0, false, false),
                new Cell(0, 1, false, true),
                new Cell(0, 2, false, true),
                new Cell(0, 3, true, false)},
            {new Cell(1, 0, false, true),
                new Cell(1, 1, true, true),
                new Cell(1, 2, true, false),
                new Cell(1, 3, true, false)},
            {new Cell(2, 0, false, false),
                new Cell(2, 1, true, true),
                new Cell(2, 2, false, false),
                new Cell(2, 3, true, true)},
            {new Cell(3, 0, false, true),
                new Cell(3, 1, false, true),
                new Cell(3, 2, true, true),
                new Cell(3, 3, true, true)}
        };
        return new Maze(4, 4, grid);
    }
}
