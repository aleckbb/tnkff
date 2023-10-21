package edu.hw2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Task2.Rectangle()),
            Arguments.of(new Task2.Square()),
            Arguments.of(new Task2.Rectangle(10, 10)),
            Arguments.of(new Task2.Square(10))
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Task2.Rectangle rect) {
        rect.setWidth(20);
        rect.setHeight(10);

        assertThat(rect.area()).isEqualTo(200.0);
    }

    static Arguments[] Square() {
        return new Arguments[] {
            Arguments.of(new Task2.Square()),
            Arguments.of(new Task2.Square(10))
        };
    }

    @ParameterizedTest
    @MethodSource("Square")
    void rectangleArea(Task2.Square S) {
        S.setSide(20);

        assertThat(S.area()).isEqualTo(400.0);
    }
}
