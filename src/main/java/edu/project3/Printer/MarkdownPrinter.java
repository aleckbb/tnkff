package edu.project3.Printer;

import edu.project3.model.Statistic;
import edu.project3.util.Util;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.OffsetDateTime;
import java.util.Map;

public class MarkdownPrinter implements Printer {

    public static final String METRIC = "Metric";

    @Override
    public <T> String print(Statistic<T> statistic) {
        StringBuilder sb = new StringBuilder();
        sb.append("#### ").append(statistic.title()).append("\n");
        int maxKeyColLength = statistic.keyName().length();
        int maxValColLength = statistic.valueName().length();


        if (statistic.statistic() instanceof Map) {
            for (Map.Entry<String, Integer> ent : ((Map<String, Integer>) statistic.statistic()).entrySet()) {
                if (maxKeyColLength < ent.getKey().length()) {
                    maxKeyColLength = ent.getKey().length();
                }
                if (maxValColLength < ent.getValue().toString().length()) {
                    maxValColLength = ent.getValue().toString().length();
                }
            }
            sb.append("|").append(Util.middleAlignmentFixSize(statistic.keyName(), " ", maxKeyColLength))
                .append("|").append(Util.rightAlignmentFixSize(statistic.valueName(), " ", maxValColLength))
                .append("|").append("\n");
            sb.append("|:").append(Util.middleAlignmentFixSize("", "-", maxKeyColLength - 2))
                .append(":|").append(Util.rightAlignmentFixSize(":", "-", maxValColLength))
                .append("|").append("\n");

            for (Map.Entry<String, Integer> ent : ((Map<String, Integer>) statistic.statistic()).entrySet()) {
                sb.append("|").append(Util.middleAlignmentFixSize(ent.getKey(), " ", maxKeyColLength))
                    .append("|").append(Util.rightAlignmentFixSize(ent.getValue().toString(), " ", maxValColLength))
                    .append("|").append("\n");
            }
        }



        if (statistic.statistic() instanceof Double
            || statistic.statistic() instanceof Integer
            || statistic.statistic() instanceof OffsetDateTime) {
            String statisticString = statistic.statistic().toString();
            if (statistic.statistic() instanceof Double) {
                DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
                decimalFormatSymbols.setDecimalSeparator('.');
                DecimalFormat df = new DecimalFormat("0.00", decimalFormatSymbols);
                statisticString = df.format(statistic.statistic());
            }
            maxValColLength = Math.max(statisticString.length(), maxValColLength);
            maxKeyColLength = Math.max(METRIC.length(), maxKeyColLength);


            sb.append("|").append(Util.middleAlignmentFixSize(METRIC, " ", maxKeyColLength))
                .append("|").append(Util.rightAlignmentFixSize(statistic.valueName(), " ", maxValColLength))
                .append("|").append("\n");
            sb.append("|:").append(Util.middleAlignmentFixSize("", "-", maxKeyColLength - 2))
                .append(":|").append(Util.rightAlignmentFixSize(":", "-", maxValColLength))
                .append("|").append("\n");

            sb.append("|").append(Util.middleAlignmentFixSize(statistic.keyName(), " ", maxKeyColLength))
                .append("|").append(Util.middleAlignmentFixSize(statisticString, " ", maxValColLength))
                .append("|").append("\n\n");
        }
        return sb.toString();
    }
}
