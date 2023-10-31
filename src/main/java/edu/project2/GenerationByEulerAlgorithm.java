package edu.project2;

import java.util.Random;

public class GenerationByEulerAlgorithm implements Generator {
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

    private void modifyLastRow(Cell[] cellsRow) {
        for (int i = 0; i < cellsRow.length; ++i) {
            cellsRow[i].setWallBottom(true);
        }
        for (int i = 0; i < cellsRow.length - 1; ++i) {
            if (cellsRow[i].getWallRight() && cellsRow[i].getSet() != cellsRow[i + 1].getSet()) {
                cellsRow[i].setWallRight(false);
            }
        }
        for (int i = 0; i < cellsRow.length - 1; ++i) {
            cellsRow[i + 1].setSet(cellsRow[i].getSet());
        }
    }

    private int generationOneRow(Cell[] cellsRow, int set, int x) {
        Random wallOrNo = new Random();
        if (x == 0) {
            for (int y = 0; y < cellsRow.length; ++y) {
                cellsRow[y] = new Cell(x, y);
                cellsRow[y].setSet(set);
                set++;
            }
        }
        else{
            for (int y = 0; y < cellsRow.length; ++y) {
                if (cellsRow[y].getSet() == 0) {
                    cellsRow[y].setSet(set);
                    set++;
                }
            }
        }
        buildWallRight(cellsRow, wallOrNo);
        buildWallBottom(cellsRow, wallOrNo);
        cellsRow[cellsRow.length - 1].setWallRight(true);
        return set;
    }

    private void buildWallBottom(Cell[] cellsRow, Random wallOrNo) {
        for (int y = 0; y < cellsRow.length; ++y) {
            if (wallOrNo.nextInt(2) == 1 && hasMoreOneCellWithoutWallBottom(cellsRow, cellsRow[y].getSet())) {
                cellsRow[y].setWallBottom(true);
            }
        }
    }

    private void buildWallRight(Cell[] cellsRow, Random wallOrNo) {
        for (int y = 0; y < cellsRow.length - 1; ++y) {
            if (wallOrNo.nextInt(2) == 1) {
                cellsRow[y].setWallRight(true);
            } else {
                if (cellsRow[y].getSet() == cellsRow[y + 1].getSet()) {
                    cellsRow[y].setWallRight(true);
                } else {
                    cellsRow[y + 1].setSet(cellsRow[y].getSet());
                }
            }
        }
    }

    public Cell[] preparingLineForNextStage(Cell[] cells, int nRow) {
        Cell[] newCells = copyRow(cells, nRow);
        for (int i = 0; i < cells.length; ++i) {
            newCells[i].setWallRight(false);
            if (newCells[i].getWallBottom()) {
                newCells[i].setWallBottom(false);
                newCells[i].setSet(0);
            }
        }
        return newCells;
    }

    public Cell[] copyRow(Cell[] cells, int nRow) {
        Cell[] newCells = new Cell[cells.length];
        for (int i = 0; i < cells.length; ++i) {
            newCells[i] = new Cell(nRow, i);
            newCells[i].copyCell(cells[i]);
        }
        return newCells;
    }

    public boolean hasMoreOneCellWithoutWallBottom(Cell[] cells, int set) {
        boolean isMoreOneCellWithoutWallBottom = false;
        int cnt = 0;
        for (int i = 0; i < cells.length && !isMoreOneCellWithoutWallBottom; ++i) {
            if (cells[i].getSet() == set && !cells[i].getWallBottom()) {
                cnt++;
            }
            if (cnt == 2) {
                isMoreOneCellWithoutWallBottom = true;
            }
        }
        return isMoreOneCellWithoutWallBottom;
    }
}
