package edu.hw9.Task1;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class StatsCollector {

    private final BlockingQueue<Metric> collector;

    public StatsCollector(int collectorSize) {
        collector = new LinkedBlockingDeque<>(collectorSize);
    }

    public void push(String metricName, double[] data) {
        try {
            collector.put(new Metric(metricName, data));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String collectStats() {
        Metric metric;
        try {
            metric = collector.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return List.of(
            metric.getMetricName(),
            Double.toString(metric.summ()),
            Double.toString(metric.avg()),
            Double.toString(metric.max()),
            Double.toString(metric.min())
        ).toString();
    }
}
