package edu.hw9.Task1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class StatsCollector {

    private final BlockingQueue<Metric> collector;

    public StatsCollector(int countOfThreads){
        collector = new LinkedBlockingDeque<>(countOfThreads);
    }

    public void push(String metricName, double[] data){
        try {
            collector.put(new Metric(metricName, data));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public BlockingQueue<Metric> stats(){
        return collector;
    }


}
