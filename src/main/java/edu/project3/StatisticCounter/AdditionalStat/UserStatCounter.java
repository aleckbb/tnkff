package edu.project3.StatisticCounter.AdditionalStat;

import edu.project3.StatisticCounter.StatisticCounter;
import edu.project3.model.LogRecord;
import edu.project3.model.Statistic;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserStatCounter implements StatisticCounter<Map<String, Integer>> {
    public static final String TITLE = "UserStatCounter";
    public static final String KEY_NAME = "User";
    public static final String VALUE_NAME = "Quantity";

    @Override
    public Statistic<Map<String, Integer>> countStatistic(List<LogRecord> logRecords) {
        Map<String, Integer> ans = new HashMap<>();
        for (var log : logRecords) {
            ans.merge(log.user(), 1, Integer::sum);
        }

        return new Statistic<>(TITLE, KEY_NAME, VALUE_NAME, ans);
    }
}
