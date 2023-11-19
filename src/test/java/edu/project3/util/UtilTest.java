package edu.project3.util;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilTest {

    public static final String QWER = "qwer";

    private static Stream<Arguments> provideStringsForMiddleAlignmentTest() {
        return Stream.of(
            Arguments.of(
                "**qwer**",
                QWER
            ),
            Arguments.of(
                "*qwer",
                QWER
            )
        );
    }


    @ParameterizedTest
    @DisplayName("middleAlignmentFixSizeTest")
    @MethodSource("provideStringsForMiddleAlignmentTest")
    void middleAlignmentFixSize(String expected, String input) {
        var res =  Util.middleAlignmentFixSize(input,"*", expected.length());

        assertEquals(expected, res);
    }

    private static Stream<Arguments> provideStringsForRightAlignmentFixSizeTest() {
        return Stream.of(
            Arguments.of(
                "****qwer",
                QWER
            ),
            Arguments.of(
                "*qwer",
                QWER
            )
        );
    }

    @ParameterizedTest
    @DisplayName("rightAlignmentFixSizeTest")
    @MethodSource("provideStringsForRightAlignmentFixSizeTest")
    void rightAlignmentFixSize(String expected, String input) {
        var res =  Util.rightAlignmentFixSize(input,"*", expected.length());

        assertEquals(expected, res);
    }
}
