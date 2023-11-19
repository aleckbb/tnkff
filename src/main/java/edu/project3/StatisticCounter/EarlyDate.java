package edu.project3.StatisticCounter;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistic;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;

public class EarlyDate implements StatisticCounter<OffsetDateTime> {
    public static final String EARLY_DATE = "EarlyDate";
    public static final String TITLE = EARLY_DATE;
    public static final String KEY_NAME = EARLY_DATE;
    public static final String VALUE_NAME = "Quantity";

    @Override
    public Statistic<OffsetDateTime> countStatistic(List<LogRecord> logRecords) {
        return new Statistic<>(TITLE, KEY_NAME, VALUE_NAME, logRecords.stream()
            .map(LogRecord::date)
            .min(Comparator.naturalOrder()).orElse(null));
    }
}
