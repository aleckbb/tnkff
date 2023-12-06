package edu.hw9.Task1;

import java.util.Arrays;

public class Metric {
    private String metricName;
    private double[] data;

    public Metric(String metricName, double[] data) {
        this.metricName = metricName;
        this.data = data;
    }

    public String getMetricName() {
        return metricName;
    }

    public double[] getData() {
        return data;
    }

    public double summ() {
        return Arrays.stream(data).sum();
    }

    public double avg() {
        return Arrays.stream(data).average().orElse(0);
    }

    public double max() {
        return Arrays.stream(data).max().orElse(0);
    }

    public double min() {
        return Arrays.stream(data).min().orElse(0);
    }
}
