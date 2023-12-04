package edu.project4.SomeFractals;

import edu.project4.UsefulClasses.Point;

@SuppressWarnings("MagicNumber")
public class Fractals {
    private Fractals() {
    }

    public static final Fractal LINEAR =
        (coefficients, point) -> new Point(point.x(), point.y());

    public static final Fractal SINUSOIDAL =
        (coefficients, point) -> new Point(Math.sin(point.x()), Math.sin(point.y()));

    public static final Fractal CYLINDER =
        (coefficients, point) -> new Point(Math.sin(point.x()), point.y());

    public static final Fractal WAVES =
        (coefficients, point) -> new Point(
            point.x() + coefficients.b() * Math.sin(point.y() / coefficients.c() * coefficients.c()),
            point.y() + coefficients.e() * Math.sin(point.x() / coefficients.f() * coefficients.f())
        );

    public static final Fractal SPHERICAL =
        (coefficients, point) -> {
            double rSquared = point.x() * point.x() + point.y() * point.y();
            return new Point(point.x() / rSquared, point.y() / rSquared);
        };

    public static final Fractal SWIRL =
        (coefficients, point) -> {
            double rSquared = point.x() * point.x() + point.y() * point.y();
            double sinRSquared = Math.sin(rSquared);
            double cosRSquared = Math.cos(rSquared);
            return new Point(
                point.x() * sinRSquared - point.y() * cosRSquared,
                point.x() * cosRSquared + point.y() * sinRSquared
            );
        };

    public static final Fractal HORSESHO =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            return new Point(
                (point.x() * point.x() - point.y() * point.y()) / r,
                2 * point.x() * point.y() / r
            );
        };

    public static final Fractal POPCORN =
        (coefficients, point) -> new Point(
            point.x() + coefficients.c() * Math.sin(Math.tan(3 * point.y())),
            point.y() + coefficients.f() * Math.sin(Math.tan(3 * point.x()))
        );

    public static final Fractal BUBBLE =
        (coefficients, point) -> {
            double rSquared = point.x() * point.x() + point.y() * point.y();
            return new Point(4 * point.x() / (rSquared + 4), 4 * point.y() / (rSquared + 4));
        };

    public static final Fractal FISHEYE =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            return new Point(2 * point.y() / (r + 1), 2 * point.x() / (r + 1));
        };

    public static final Fractal EYEFISH =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            return new Point(2 * point.x() / (r + 1), 2 * point.y() / (r + 1));
        };

    public static final Fractal TANGENT =
        (coefficients, point) -> new Point(
            Math.sin(point.x()) / Math.cos(point.y()),
            Math.tan(point.y())
        );

    public static final Fractal EXPONENTIAL =
        (coefficients, point) -> new Point(
            Math.exp(point.x() - 1) * Math.cos(Math.PI * point.y()),
            Math.exp(point.x() - 1) * Math.sin(Math.PI * point.y())
        );

    public static final Fractal HANDKERCHIEF =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            double theta = Math.atan(point.x() / point.y());
            return new Point(r * Math.sin(theta + r), r * Math.cos(theta - r));
        };

    public static final Fractal HEART =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            double theta = Math.atan(point.x() / point.y());
            return new Point(r * Math.sin(theta * r), r * (-1) * Math.cos(theta * r));
        };

    public static final Fractal DISC =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            double theta = Math.atan(point.x() / point.y());
            return new Point(theta / Math.PI * Math.sin(Math.PI * r), theta / Math.PI * Math.cos(Math.PI * r));
        };

    public static final Fractal HYPERBOLIC =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            double theta = Math.atan(point.x() / point.y());
            return new Point(Math.sin(theta) / r, r * Math.cos(theta));
        };

    public static final Fractal DIAMOND =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            double theta = Math.atan(point.x() / point.y());
            return new Point(Math.sin(theta) * Math.cos(r), Math.cos(theta) * Math.sin(r));
        };

    public static final Fractal SPIRAL =
        (coefficients, point) -> {
            double r = Math.sqrt(point.x() * point.x() + point.y() * point.y());
            double theta = Math.atan(point.x() / point.y());
            return new Point((Math.cos(theta) + Math.sin(r)) / r, (Math.sin(theta) - Math.cos(r)) / r);
        };

}
