package edu.project3.model;

public record Statistic<T>(String title, String keyName, String valueName, T statistic) { }
