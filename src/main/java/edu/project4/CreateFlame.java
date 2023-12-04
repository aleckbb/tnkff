package edu.project4;

import edu.project4.ReadyFunctions.Functions;
import edu.project4.UsefulClasses.Function;
import edu.project4.UsefulClasses.Pixel;
import edu.project4.UsefulClasses.Point;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CreateFlame {
    private static final double X_MIN = -1.777;
    private static final double X_MAX = 1.777;
    private static final double Y_MIN = -1;
    private static final double Y_MAX = 1;
    private static final int RES_X = 1920;
    private static final int RES_Y = 1080;
    private static final int MINIMAL_ITERATION = 20;
    private final ReentrantLock lock = new ReentrantLock();
    private final ExecutorService executorService;
    private final int samples;
    private final int iterPerSample;
    private final int threads;
    private final int samplesPerThread;
    private final double gamma;
    private final boolean symmetry;
    private final Pixel[][] matrixDisplay;

    public CreateFlame(int countOfThreads, int samples, int iter, double gamma, boolean symmetry) {
        threads = countOfThreads;
        executorService = Executors.newFixedThreadPool(threads);
        this.samples = samples;
        iterPerSample = iter;
        samplesPerThread = this.samples / threads;
        this.gamma = gamma;
        this.symmetry = symmetry;
        matrixDisplay = new Pixel[RES_X][RES_Y];
        for (int i = 0; i < RES_X; ++i) {
            for (int j = 0; j < RES_Y; ++j) {
                matrixDisplay[i][j] = new Pixel();
            }
        }
    }

    public void render() {
        var tasks = Stream.generate(() -> CompletableFuture.runAsync(
                    this::renderPerThread,
                    executorService
                )
            )
            .limit(threads)
            .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(tasks).join();
    }

    public Pixel[][] getMatrixDisplay() {
        return matrixDisplay;
    }

    public Pixel[][] gammaCorrection(Pixel[][] pixels) {
        var tasks = IntStream.range(0, threads)
            .mapToObj(numberOfThreads -> CompletableFuture.runAsync(
                    () -> {
                        double max = 0;
                        for (int x = numberOfThreads; x < RES_X; x += threads) {
                            for (int y = 0; y < RES_Y; ++y) {
                                if (pixels[x][y].getCountHit() != 0) {
                                    pixels[x][y].setNormal(Math.log10(pixels[x][y].getCountHit()));
                                    if (pixels[x][y].getNormal() > max) {
                                        max = pixels[x][y].getNormal();
                                    }
                                }
                            }
                        }
                        for (int x = numberOfThreads; x < RES_X; x += threads) {
                            for (int y = 0; y < RES_Y; ++y) {
                                pixels[x][y].setNormal(pixels[x][y].getNormal() / max);
                                double gammaCoefficient = Math.pow(pixels[x][y].getNormal(), (1.0 / gamma));
                                pixels[x][y].getRgb().setRed(
                                    (int) (pixels[x][y].getRgb().getRed() * gammaCoefficient)
                                );
                                pixels[x][y].getRgb().setGreen(
                                    (int) (pixels[x][y].getRgb().getGreen() * gammaCoefficient)
                                );
                                pixels[x][y].getRgb().setBlue(
                                    (int) (pixels[x][y].getRgb().getBlue() * gammaCoefficient)
                                );
                            }
                        }
                    },
                    executorService
                )
            )
            .limit(threads)
            .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(tasks).join();
        return pixels;
    }

    private void renderPerThread() {
        for (int i = 0; i < samplesPerThread; ++i) {
            double newX = ThreadLocalRandom.current().nextDouble(X_MIN, X_MAX);
            double newY = ThreadLocalRandom.current().nextDouble(Y_MIN, Y_MAX);
            for (int j = -MINIMAL_ITERATION; j < iterPerSample; ++j) {
                Function function = Functions.getFunction();
                Point point = transformation(function, newX, newY, j);
                newX = point.x();
                newY = point.y();
                if (j >= 0 && isPointInRange(point)) {
                    Point newPoint = findLocationOnDisplay(point);
                    if (isPointInDisplay(newPoint)) {
                        try {
                            lock.lock();
                            int x = (int) newPoint.x();
                            int y = (int) newPoint.y();
                            matrixDisplay[x][y].incrementCountHit();
                            matrixDisplay[x][y].getRgb().setRed(
                                (matrixDisplay[x][y].getRgb().getRed() + function.rgb().getRed()) / 2
                            );
                            matrixDisplay[x][y].getRgb().setGreen(
                                (matrixDisplay[x][y].getRgb().getGreen() + function.rgb().getGreen()) / 2
                            );
                            matrixDisplay[x][y].getRgb().setBlue(
                                (matrixDisplay[x][y].getRgb().getBlue() + function.rgb().getBlue()) / 2
                            );
                        } finally {
                            lock.unlock();
                        }
                    }
                }
            }
        }
    }

    @SuppressWarnings("MagicNumber")
    private Point transformation(Function function, double x, double y, int numberOfIter) {
        // Афинное преобразовение
        double newX = 0;
        double newY = 0;
        double currentX = function.coefficients().a() * x + function.coefficients().b() * y
            + function.coefficients().c();
        double currentY = function.coefficients().d() * x + function.coefficients().e() * y
            + function.coefficients().f();

        // Нелинейное преобразование
        var variants = function.variants();
        for (var variant : variants) {
            Point point = variant.type().apply(
                function.coefficients(),
                new Point(currentX, currentY)
            );
            newX = variant.weight() * point.x();
            newY = variant.weight() * point.y();
        }
        // Постпреобразование
        double finalX = function.finalTransformation().a() * newX + function.finalTransformation().b() * newY
            + function.coefficients().c();
        double finalY = function.finalTransformation().d() * newX + function.finalTransformation().e() * newY
            + function.finalTransformation().f();

        // Поддержка симметрии
        if (symmetry) {
            if (numberOfIter % 4 == 0) {
                finalX *= -1;
            } else if (numberOfIter % 3 == 0) {
                finalX *= -1;
                finalY *= -1;
            } else if (numberOfIter % 2 == 0) {
                finalY *= -1;
            }
        }
        return new Point(finalX, finalY);
    }

    private boolean isPointInRange(Point point) {
        return X_MIN <= point.x() && point.x() <= X_MAX
            && Y_MIN <= point.y() && point.y() <= Y_MAX;
    }

    private Point findLocationOnDisplay(Point point) {
        return new Point(
            (point.x() - X_MIN) / (X_MAX - X_MIN) * RES_X,
            (point.y() - Y_MIN) / (Y_MAX - Y_MIN) * RES_Y
        );
    }

    private boolean isPointInDisplay(Point point) {
        return 0 <= point.x() && point.x() <= RES_X
            && 0 <= point.y() && point.y() <= RES_Y;
    }
}
