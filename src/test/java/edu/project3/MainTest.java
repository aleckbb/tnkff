package edu.project3;

import edu.project3.Parser.ParserImpl;
import edu.project3.Printer.AdocPrinter;
import edu.project3.Printer.MarkdownPrinter;
import edu.project3.Printer.Printer;
import edu.project3.StatisticCounter.AdditionalStat.HttpUserAgentStatisticCounter;
import edu.project3.StatisticCounter.AdditionalStat.UserStatCounter;
import edu.project3.StatisticCounter.AvgBodyBytes;
import edu.project3.StatisticCounter.CountRequests;
import edu.project3.StatisticCounter.EarlyDate;
import edu.project3.StatisticCounter.LateDate;
import edu.project3.StatisticCounter.MostFrequentCode;
import edu.project3.StatisticCounter.MostFrequentResource;
import edu.project3.StatisticCounter.StatisticCounter;
import edu.project3.model.LogRecord;
import edu.project3.model.Statistic;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    public static final String dir = "src/test/java/edu/project3/testData";
    public static final List<LogRecord> testLogRecords = List.of(
        new LogRecord(
            "37.26.93.214",
            "-",
            OffsetDateTime.parse("2015-05-17T08:05:52Z"),
            "GET",
            "/downloads/product_2",
            "200",
            3318,
            "\"-\"",
            "Go1.1packagehttp"
        ),
        new LogRecord(
            "23.23.226.37",
            "-",
            OffsetDateTime.parse("2015-05-17T08:05:19Z"),
            "GET",
            "/downloads/product_2",
            "200",
            2578,
            "\"-\"",
            "urlgrabber/3.9.1yum/3.4.3"
        )
    );

    @Test
    void parserTest() {
        // Given
        ParserImpl parser = new ParserImpl();

        // When
        var res = parser.parse(dir, OffsetDateTime.MIN, OffsetDateTime.now());

        // Then
        assertEquals(testLogRecords, res);
    }

    private static Stream<Arguments> provideLogRecordsToCountStatistic() {
        var mapForHttpUserAgentTest = new HashMap<String, Integer>();
        mapForHttpUserAgentTest.put("urlgrabber/3.9.1yum/3.4.3", 1);
        mapForHttpUserAgentTest.put("Go1.1packagehttp", 1);

        var mapForMostFrequentCodeTest = new HashMap<String, Integer>();
        mapForMostFrequentCodeTest.put("200", 2);

        var mapForMostFrequentResourceTest = new HashMap<String, Integer>();
        mapForMostFrequentResourceTest.put("/downloads/product_2", 2);

        var mapForUserStatCounterTest = new HashMap<String, Integer>();
        mapForUserStatCounterTest.put("-", 2);



        return Stream.of(
            Arguments.of(
                new Statistic<>(
                    "AvgBodyBytes",
                    "AvgBodyBytes",
                    "Value",
                    2948.0
                ),
                new AvgBodyBytes(),
                testLogRecords
            ),
            Arguments.of(
                new Statistic<>(
                    "CountRequests",
                    "Requests",
                    "Quantity",
                    2
                ),
                new CountRequests(),
                testLogRecords
            ),
            Arguments.of(
                new Statistic<>(
                    "EarlyDate",
                    "EarlyDate",
                    "Quantity",
                    OffsetDateTime.parse("2015-05-17T08:05:19Z")
                ),
                new EarlyDate(),
                testLogRecords
            ),
            Arguments.of(
                new Statistic<>(
                    "LateDate",
                    "LateDate",
                    "Quantity",
                    OffsetDateTime.parse("2015-05-17T08:05:52Z")
                ),
                new LateDate(),
                testLogRecords
            ),
            Arguments.of(
                new Statistic<>(
                    "MostFrequentCode",
                    "Code",
                    "Quantity",
                    mapForMostFrequentCodeTest
                ),
                new MostFrequentCode(),
                testLogRecords
            ),
            Arguments.of(
                new Statistic<>(
                    "MostFrequentResource",
                    "Resource",
                    "Quantity",
                    mapForMostFrequentResourceTest
                ),
                new MostFrequentResource(),
                testLogRecords
            ),
            Arguments.of(
                new Statistic<>(
                    "HttpUserAgentStatisticCounter",
                    "HttpUserAgent",
                    "Quantity",
                    mapForHttpUserAgentTest
                ),
                new HttpUserAgentStatisticCounter(),
                testLogRecords
            ),
            Arguments.of(
                new Statistic<>(
                    "UserStatCounter",
                    "User",
                    "Quantity",
                    mapForUserStatCounterTest
                ),
                new UserStatCounter(),
                testLogRecords
            )
        );
    }

    @ParameterizedTest
    @DisplayName("statisticCounterTest")
    @MethodSource("provideLogRecordsToCountStatistic")
    <T> void statisticTest(Statistic<T> expected, StatisticCounter<T> statisticCounter, List<LogRecord> logRecords) {
        // When
        var res = statisticCounter.countStatistic(logRecords);

        // Then
        assertEquals(expected, res);
    }

    private static Stream<Arguments> provideStatisticsForMarkdownPrinter() {
        var mapForHttpUserAgentTest = new HashMap<String, Integer>();
        mapForHttpUserAgentTest.put("urlgrabber/3.9.1yum/3.4.3", 1);
        mapForHttpUserAgentTest.put("Go1.1packagehttp", 1);

        var mapForMostFrequentCodeTest = new HashMap<String, Integer>();
        mapForMostFrequentCodeTest.put("200", 2);

        var mapForMostFrequentResourceTest = new HashMap<String, Integer>();
        mapForMostFrequentResourceTest.put("/downloads/product_2", 2);

        var mapForUserStatCounterTest = new HashMap<String, Integer>();
        mapForUserStatCounterTest.put("-", 2);



        return Stream.of(
            Arguments.of(
                new Statistic<>(
                    "AvgBodyBytes",
                    "AvgBodyBytes",
                    "Value",
                    2948.0
                ),
                "#### AvgBodyBytes\n" +
                    "|   Metric   |  Value|\n" +
                    "|:----------:|------:|\n" +
                    "|AvgBodyBytes|2948.00|\n\n"
            ),
            Arguments.of(
                new Statistic<>(
                    "CountRequests",
                    "Requests",
                    "Quantity",
                    2
                ),
                "#### CountRequests\n" +
                    "| Metric |Quantity|\n" +
                    "|:------:|-------:|\n" +
                    "|Requests|    2   |\n\n"
            ),
            Arguments.of(
                new Statistic<>(
                    "EarlyDate",
                    "EarlyDate",
                    "Quantity",
                    OffsetDateTime.parse("2015-05-17T08:05:19Z")
                ),
                "#### EarlyDate\n" +
                    "|  Metric |            Quantity|\n" +
                    "|:-------:|-------------------:|\n" +
                    "|EarlyDate|2015-05-17T08:05:19Z|\n\n"
            ),
            Arguments.of(
                new Statistic<>(
                    "LateDate",
                    "LateDate",
                    "Quantity",
                    OffsetDateTime.parse("2015-05-17T08:05:52Z")
                ),
                "#### LateDate\n" +
                    "| Metric |            Quantity|\n" +
                    "|:------:|-------------------:|\n" +
                    "|LateDate|2015-05-17T08:05:52Z|\n\n"
            ),
            Arguments.of(
                new Statistic<>(
                    "MostFrequentCode",
                    "Code",
                    "Quantity",
                    mapForMostFrequentCodeTest
                ),
                "#### MostFrequentCode\n" +
                    "|Code|Quantity|\n" +
                    "|:--:|-------:|\n" +
                    "| 200|       2|\n"
            ),
            Arguments.of(
                new Statistic<>(
                    "MostFrequentResource",
                    "Resource",
                    "Quantity",
                    mapForMostFrequentResourceTest
                ),
                "#### MostFrequentResource\n" +
                    "|      Resource      |Quantity|\n" +
                    "|:------------------:|-------:|\n" +
                    "|/downloads/product_2|       2|\n"
            ),
            Arguments.of(
                new Statistic<>(
                    "HttpUserAgentStatisticCounter",
                    "HttpUserAgent",
                    "Quantity",
                    mapForHttpUserAgentTest
                ),
                "#### HttpUserAgentStatisticCounter\n" +
                    "|      HttpUserAgent      |Quantity|\n" +
                    "|:-----------------------:|-------:|\n" +
                    "|urlgrabber/3.9.1yum/3.4.3|       1|\n" +
                    "|     Go1.1packagehttp    |       1|\n"
            ),
            Arguments.of(
                new Statistic<>(
                    "UserStatCounter",
                    "User",
                    "Quantity",
                    mapForUserStatCounterTest
                ),
                "#### UserStatCounter\n" +
                    "|User|Quantity|\n" +
                    "|:--:|-------:|\n" +
                    "|  - |       2|\n"
            )
        );
    }





    @ParameterizedTest
    @DisplayName("markdownPrinterTest")
    @MethodSource("provideStatisticsForMarkdownPrinter")
    <T> void markdownPrinterTest(Statistic<T> statistic, String expected) {
        // Given
        Printer printer = new MarkdownPrinter();

        // When
        var res = printer.print(statistic);

        // Then
        assertEquals(expected, res);
    }

    private static Stream<Arguments> provideStatisticsForAdocPrinter() {
        var mapForHttpUserAgentTest = new HashMap<String, Integer>();
        mapForHttpUserAgentTest.put("urlgrabber/3.9.1yum/3.4.3", 1);
        mapForHttpUserAgentTest.put("Go1.1packagehttp", 1);

        var mapForMostFrequentCodeTest = new HashMap<String, Integer>();
        mapForMostFrequentCodeTest.put("200", 2);

        var mapForMostFrequentResourceTest = new HashMap<String, Integer>();
        mapForMostFrequentResourceTest.put("/downloads/product_2", 2);

        var mapForUserStatCounterTest = new HashMap<String, Integer>();
        mapForUserStatCounterTest.put("-", 2);



        return Stream.of(
            Arguments.of(
                new Statistic<>(
                    "AvgBodyBytes",
                    "AvgBodyBytes",
                    "Value",
                    2948.0
                ),
                "= AvgBodyBytes\n" +
                    "[cols=2]\n" +
                    "|====\n" +
                    "|Metric |Value\n" +
                    "\n" +
                    "|AvgBodyBytes\n" +
                    "|2948.0\n" +
                    "|===="
            ),
            Arguments.of(
                new Statistic<>(
                    "CountRequests",
                    "Requests",
                    "Quantity",
                    2
                ),
                "= CountRequests\n" +
                    "[cols=2]\n" +
                    "|====\n" +
                    "|Metric |Quantity\n" +
                    "\n" +
                    "|Requests\n" +
                    "|2\n" +
                    "|===="
            ),
            Arguments.of(
                new Statistic<>(
                    "EarlyDate",
                    "EarlyDate",
                    "Quantity",
                    OffsetDateTime.parse("2015-05-17T08:05:19Z")
                ),
                "= EarlyDate\n" +
                    "[cols=2]\n" +
                    "|====\n" +
                    "|Metric |Quantity\n" +
                    "\n" +
                    "|EarlyDate\n" +
                    "|2015-05-17T08:05:19Z\n" +
                    "|===="
            ),
            Arguments.of(
                new Statistic<>(
                    "LateDate",
                    "LateDate",
                    "Quantity",
                    OffsetDateTime.parse("2015-05-17T08:05:52Z")
                ),
                "= LateDate\n" +
                    "[cols=2]\n" +
                    "|====\n" +
                    "|Metric |Quantity\n" +
                    "\n" +
                    "|LateDate\n" +
                    "|2015-05-17T08:05:52Z\n" +
                    "|===="
            ),
            Arguments.of(
                new Statistic<>(
                    "MostFrequentCode",
                    "Code",
                    "Quantity",
                    mapForMostFrequentCodeTest
                ),
                "= MostFrequentCode\n" +
                    "[cols=2]\n" +
                    "|====\n" +
                    "|Code |Quantity\n" +
                    "\n" +
                    "|200\n" +
                    "|2\n" +
                    "\n" +
                    "|===="
            ),
            Arguments.of(
                new Statistic<>(
                    "MostFrequentResource",
                    "Resource",
                    "Quantity",
                    mapForMostFrequentResourceTest
                ),
                "= MostFrequentResource\n" +
                    "[cols=2]\n" +
                    "|====\n" +
                    "|Resource |Quantity\n" +
                    "\n" +
                    "|/downloads/product_2\n" +
                    "|2\n" +
                    "\n" +
                    "|===="
            ),
            Arguments.of(
                new Statistic<>(
                    "HttpUserAgentStatisticCounter",
                    "HttpUserAgent",
                    "Quantity",
                    mapForHttpUserAgentTest
                ),
                "= HttpUserAgentStatisticCounter\n" +
                    "[cols=2]\n" +
                    "|====\n" +
                    "|HttpUserAgent |Quantity\n" +
                    "\n" +
                    "|urlgrabber/3.9.1yum/3.4.3\n" +
                    "|1\n" +
                    "\n" +
                    "|Go1.1packagehttp\n" +
                    "|1\n" +
                    "\n" +
                    "|===="
            ),
            Arguments.of(
                new Statistic<>(
                    "UserStatCounter",
                    "User",
                    "Quantity",
                    mapForUserStatCounterTest
                ),
                "= UserStatCounter\n" +
                    "[cols=2]\n" +
                    "|====\n" +
                    "|User |Quantity\n" +
                    "\n" +
                    "|-\n" +
                    "|2\n" +
                    "\n" +
                    "|===="
            )
        );
    }

    @ParameterizedTest
    @DisplayName("adocPrinterTest")
    @MethodSource("provideStatisticsForAdocPrinter")
    <T> void adocPrinterTest(Statistic<T> statistic, String expected) {
        // Given
        Printer printer = new AdocPrinter();

        // When
        var res = printer.print(statistic);

        // Then
        assertEquals(expected, res);
    }

}
