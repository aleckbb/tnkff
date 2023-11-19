package edu.project3.StatisticCounter;

import edu.project3.model.LogRecord;
import edu.project3.model.Statistic;
import java.util.List;

public interface StatisticCounter<T> {
    Statistic<T> countStatistic(List<LogRecord> logRecords);
}
