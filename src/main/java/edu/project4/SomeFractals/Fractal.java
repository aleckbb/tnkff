package edu.project4.SomeFractals;

import edu.project4.UsefulClasses.Coefficients;
import edu.project4.UsefulClasses.Point;

public interface Fractal {
    Point apply(Coefficients coefficients, Point point);
}
