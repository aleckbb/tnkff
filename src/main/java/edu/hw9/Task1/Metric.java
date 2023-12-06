package edu.hw9.Task1;

public class Metric {
    private String metricName;
    private double[] data = new double[4];

    public Metric(String metricName, double[] data){
        this.metricName = metricName;
        this.data = data;
    }

    public String getMetricName() {
        return metricName;
    }

    public double[] getData() {
        return data;
    }
}
