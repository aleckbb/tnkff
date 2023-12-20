package edu.project4.UsefulClasses;

import java.util.List;

public record Function(Coefficients coefficients,
                       RGB rgb,
                       FinalTransformation finalTransformation,
                       List<Variant> variants) {
}
