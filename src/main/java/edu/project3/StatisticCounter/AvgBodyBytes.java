package edu.project3.StatisticCounter;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistic;
import java.util.List;

public class AvgBodyBytes implements StatisticCounter<Double> {
    public static final String AVG_BODY_BYTES = "AvgBodyBytes";
    public static final String TITLE = AVG_BODY_BYTES;
    public static final String KEY_NAME = AVG_BODY_BYTES;
    public static final String VALUE_NAME = "Value";

    @Override
    public Statistic<Double> countStatistic(List<LogRecord> logRecords) {
        return new Statistic<>(TITLE, KEY_NAME, VALUE_NAME, logRecords.stream()
            .mapToInt(LogRecord::bodyBytes)
            .average().orElse(0));
    }
}
