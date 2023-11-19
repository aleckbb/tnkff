package edu.project3.Printer;

import edu.project3.model.Statistic;
import java.time.OffsetDateTime;
import java.util.Map;

public class AdocPrinter implements Printer {

    public static final String END_START_TABLE = "|====";
    public static final String DOUBLE_NEXT_LINE = "\n\n";
    public static final String METRIC = "Metric";

    @Override
    public <T> String print(Statistic<T> statistic) {
        StringBuilder sb = new StringBuilder();
        sb.append("= ").append(statistic.title()).append("\n");
        sb.append("[cols=2]").append("\n").append(END_START_TABLE).append("\n");

        if (statistic.statistic() instanceof Map) {
            sb.append("|").append(statistic.keyName()).append(" |").append(statistic.valueName())
                .append(DOUBLE_NEXT_LINE);
            for (Map.Entry<String, Integer> ent : ((Map<String, Integer>) statistic.statistic()).entrySet()) {
                sb.append("|").append(ent.getKey()).append("\n").append("|").append(ent.getValue().toString())
                    .append(DOUBLE_NEXT_LINE);
            }
        }
        if (statistic.statistic() instanceof Double
            || statistic.statistic() instanceof Integer
            || statistic.statistic() instanceof OffsetDateTime) {
            sb.append("|").append(METRIC).append(" |").append(statistic.valueName()).append(DOUBLE_NEXT_LINE);
            sb.append("|").append(statistic.keyName()).append("\n").append("|").append(statistic.statistic())
                .append("\n");
        }
        sb.append(END_START_TABLE);
        return sb.toString();
    }
}
