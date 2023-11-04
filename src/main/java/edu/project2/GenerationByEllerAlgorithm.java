package edu.project2;

import java.util.Random;

@SuppressWarnings("ParameterAssignment")
public class GenerationByEllerAlgorithm
    implements Generator { // https://habr.com/ru/articles/746916/ - как работает алгоритм Эллера
    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        int set = generationOneRow(grid[0], 1, 0);
        for (int x = 1; x < height; ++x) {
            grid[x] = preparingLineForNextStage(grid[x - 1], x);
            set = generationOneRow(grid[x], set, x);
        }
        modifyLastRow(grid[height - 1]);
        return new Maze(height, width, grid);
    }

    private int generationOneRow(Cell[] cellsRow, int set, int x) {
        Random wallOrNo = new Random();
        if (x == 0) {

            for (int y = 0; y < cellsRow.length; ++y) {
                cellsRow[y] = new Cell(x, y);
                cellsRow[y].setSet(set);
                set++;
            }
        } else {
            for (Cell cell : cellsRow) {
                if (cell.getSet() == 0) {
                    cell.setSet(set);
                    set++;
                }
            }
        }
        buildWallRight(cellsRow, wallOrNo);
        buildWallBottom(cellsRow, wallOrNo);
        cellsRow[cellsRow.length - 1].setWallRight(true);
        return set;
    }

    private void buildWallRight(Cell[] cellsRow, Random wallOrNo) {
        for (int y = 0; y < cellsRow.length - 1; ++y) {
            if (wallOrNo.nextInt(2) == 1) {
                cellsRow[y].setWallRight(true);
            } else {
                if (cellsRow[y].getSet() == cellsRow[y + 1].getSet()) {
                    cellsRow[y].setWallRight(true);
                } else {
                    mergeSet(cellsRow, cellsRow[y].getSet(), cellsRow[y + 1].getSet());
                }
            }
        }
    }

    private void mergeSet(Cell[] cellsRow, int source, int destination) {
        for (Cell cell : cellsRow) {
            if (cell.getSet() == destination) {
                cell.setSet(source);
            }
        }
    }

    private void buildWallBottom(Cell[] cellsRow, Random wallOrNo) {
        for (Cell cell : cellsRow) {
            if (wallOrNo.nextInt(2) == 1 && hasMoreOneCellWithoutWallBottom(cellsRow, cell.getSet())) {
                cell.setWallBottom(true);
            }
        }
    }

    public boolean hasMoreOneCellWithoutWallBottom(Cell[] cells, int set) {
        boolean isMoreOneCellWithoutWallBottom = false;
        int cnt = 0;
        for (int y = 0; y < cells.length && !isMoreOneCellWithoutWallBottom; ++y) {
            if (cells[y].getSet() == set && !cells[y].getWallBottom()) {
                cnt++;
            }
            if (cnt == 2) {
                isMoreOneCellWithoutWallBottom = true;
            }
        }
        return isMoreOneCellWithoutWallBottom;
    }

    public Cell[] preparingLineForNextStage(Cell[] cells, int nRow) {
        Cell[] newCells = copyRow(cells, nRow);
        for (int y = 0; y < cells.length; ++y) {
            newCells[y].setWallRight(false);
            if (newCells[y].getWallBottom()) {
                newCells[y].setWallBottom(false);
                newCells[y].setSet(0);
            }
        }
        return newCells;
    }

    public Cell[] copyRow(Cell[] cells, int nRow) {
        Cell[] newCells = new Cell[cells.length];
        for (int y = 0; y < cells.length; ++y) {
            newCells[y] = new Cell(nRow, y);
            newCells[y].copyCell(cells[y]);
        }
        return newCells;
    }

    private void modifyLastRow(Cell[] cellsRow) {
        for (int y = 0; y < cellsRow.length - 1; ++y) {
            cellsRow[y].setWallBottom(true);
            if (cellsRow[y].getWallRight() && cellsRow[y].getSet() != cellsRow[y + 1].getSet()) {
                cellsRow[y].setWallRight(false);
                mergeSet(cellsRow, cellsRow[y].getSet(), cellsRow[y + 1].getSet());
            }
        }
        cellsRow[cellsRow.length - 1].setWallBottom(true);
    }
}
