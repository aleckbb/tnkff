package edu.hw1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task8 {

    final static int ONE = 1;
    final static int TWO = 2;
    final static int EIGHT = 8;
    private final static Logger LOGGER = LogManager.getLogger();

    private Task8() {
    }

    public static boolean top(int[][] a, int i, int j) {
        return (i + TWO < EIGHT && j + ONE < EIGHT && a[i + TWO][j + ONE] == ONE
            || i + TWO < EIGHT && j - ONE >= 0 && a[i + TWO][j - ONE] == ONE);
    }

    public static boolean bottom(int[][] a, int i, int j) {
        return (i - TWO >= 0 && j + ONE < EIGHT && a[i - TWO][j + ONE] == ONE
            || i - TWO >= 0 && j - ONE >= 0 && a[i - TWO][j - ONE] == ONE);
    }

    public static boolean left(int[][] a, int i, int j) {
        return (i - ONE >= 0 && j - TWO >= 0 && a[i - ONE][j - TWO] == ONE
            || i + ONE < EIGHT && j - TWO >= 0 && a[i + ONE][j - TWO] == ONE);
    }

    public static boolean right(int[][] a, int i, int j) {
        return (i - ONE >= 0 && j + TWO < EIGHT && a[i - ONE][j + TWO] == ONE
            || i + ONE < EIGHT && j + TWO < EIGHT && a[i + ONE][j + TWO] == ONE);
    }

    public static boolean knightBoardCapture(int[][] a) {
        boolean res = true;
        for (int i = 0; i < EIGHT && res; i++) {
            for (int j = 0; j < EIGHT && res; j++) {
                if (a[i][j] == ONE) {
                    if (right(a, i, j) || left(a, i, j) || bottom(a, i, j) || top(a, i, j)) {
                        res = false;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] a = {
            {0, 0, 0, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0}
        };
        LOGGER.info("Кони, расставлены так, что ни один из них не может захватить другого коня -> "
            + knightBoardCapture(a));
    }
}
