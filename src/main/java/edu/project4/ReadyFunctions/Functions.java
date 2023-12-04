package edu.project4.ReadyFunctions;

import edu.project4.SomeFractals.Fractals;
import edu.project4.UsefulClasses.Coefficients;
import edu.project4.UsefulClasses.FinalTransformation;
import edu.project4.UsefulClasses.Function;
import edu.project4.UsefulClasses.RGB;
import edu.project4.UsefulClasses.Variant;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Functions {
    private Functions() {
    }

    private static Coefficients defaultCoefficients = new Coefficients(1, 0, 0, 0, 1, 0);
    private static FinalTransformation defaulttFinalTransformation =
        new FinalTransformation(1, 0, 0, 0, 1, 0);

    private static int defaultWeight = 1;

    private static final List<Function> FUNCTIONS = List.of(
        new Function(
            Coefficients.randomCoefficients(),
            new RGB(),
            FinalTransformation.randomFinalTransformation(),
            List.of(
                new Variant(ThreadLocalRandom.current().nextInt(1, 3), Fractals.HEART),
                new Variant(ThreadLocalRandom.current().nextInt(1, 3), Fractals.EXPONENTIAL),
                new Variant(ThreadLocalRandom.current().nextInt(1, 3), Fractals.POPCORN),
                new Variant(ThreadLocalRandom.current().nextInt(1, 3), Fractals.SWIRL),
                new Variant(ThreadLocalRandom.current().nextInt(1, 3), Fractals.DISC)
            )
        ),
        new Function(
            Coefficients.randomCoefficients(),
            new RGB(),
            FinalTransformation.randomFinalTransformation(),
            List.of(
                new Variant(ThreadLocalRandom.current().nextInt(1, 3), Fractals.HEART),
                new Variant(ThreadLocalRandom.current().nextInt(1, 3), Fractals.DIAMOND)
            )
        ),
        new Function(
            Coefficients.randomCoefficients(),
            new RGB(),
            FinalTransformation.randomFinalTransformation(),
            List.of(
                new Variant(ThreadLocalRandom.current().nextInt(1, 3), Fractals.HORSESHO),
                new Variant(ThreadLocalRandom.current().nextInt(1, 3), Fractals.HANDKERCHIEF)
            )
        ),
        new Function(
            Coefficients.randomCoefficients(),
            new RGB(),
            FinalTransformation.randomFinalTransformation(),
            List.of(
                new Variant(ThreadLocalRandom.current().nextInt(1, 3), Fractals.HEART),
                new Variant(ThreadLocalRandom.current().nextInt(1, 3), Fractals.SPIRAL)
            )
        ),
        new Function(
            Coefficients.randomCoefficients(),
            new RGB(),
            FinalTransformation.randomFinalTransformation(),
            List.of(
                new Variant(ThreadLocalRandom.current().nextInt(1, 3), Fractals.SPIRAL),
                new Variant(ThreadLocalRandom.current().nextInt(1, 3), Fractals.HYPERBOLIC)
            )
        )
    );

    public static Function getFunction() {
        return FUNCTIONS.get(ThreadLocalRandom.current().nextInt(FUNCTIONS.size()));
    }
}
