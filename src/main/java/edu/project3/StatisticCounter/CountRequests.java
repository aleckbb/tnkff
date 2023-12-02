package edu.project3.StatisticCounter;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistic;
import java.util.List;

public class CountRequests implements StatisticCounter<Integer> {
    public static final String TITLE = "CountRequests";
    public static final String KEY_NAME = "Requests";
    public static final String VALUE_NAME = "Quantity";

    @Override
    public Statistic<Integer> countStatistic(List<LogRecord> logRecords) {
        return new Statistic<>(TITLE, KEY_NAME, VALUE_NAME, logRecords.size());
    }
}
