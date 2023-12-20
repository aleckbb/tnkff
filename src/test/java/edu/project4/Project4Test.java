package edu.project4;

import edu.project4.UsefulClasses.Pixel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class Project4Test {
    @Test
    @DisplayName("Изображение меняется после render")
    void test1() {
        // given
        CreateFlame createFlame = new CreateFlame(4, 10, 300, 2.2, true);
        var matrixDisplay = createFlame.getMatrixDisplay();
        var before = new Pixel[1920][1080];
        for (int x = 0; x < 1920; x++) {
            for (int y = 0; y < 1080; y++) {
                var matrixDisplayElem = matrixDisplay[x][y];
                before[x][y] = new Pixel(
                    matrixDisplayElem.getCountHit(),
                    matrixDisplayElem.getNormal(),
                    matrixDisplayElem.getRgb()
                );
            }
        }
        boolean isEquals = true;

        // when
        createFlame.render();
        for (int x = 0; x < 1920 && isEquals; x++) {
            for (int y = 0; y < 1080 && isEquals; y++) {
                if (before[x][y].getCountHit() != matrixDisplay[x][y].getCountHit()) {
                    isEquals = false;
                }
            }
        }

        // then
        assertFalse(isEquals);
    }

    @Test
    @DisplayName("Изображение меняется после gammaCorrection")
    void test2() {
        // given
        CreateFlame createFlame = new CreateFlame(4, 10, 300, 2.2, true);
        var matrixDisplay = createFlame.getMatrixDisplay();
        var before = new Pixel[1920][1080];
        for (int x = 0; x < 1920; x++) {
            for (int y = 0; y < 1080; y++) {
                var matrixDisplayElem = matrixDisplay[x][y];
                before[x][y] = new Pixel(
                    matrixDisplayElem.getCountHit(),
                    matrixDisplayElem.getNormal(),
                    matrixDisplayElem.getRgb()
                );
            }
        }
        boolean isEquals = true;

        // when
        createFlame.render();
        var image = createFlame.gammaCorrection(matrixDisplay);
        for (int x = 0; x < 1920 && isEquals; x++) {
            for (int y = 0; y < 1080 && isEquals; y++) {
                if (before[x][y].getCountHit() != image[x][y].getCountHit()) {
                    isEquals = false;
                }
            }
        }

        // then
        assertFalse(isEquals);
    }
}
