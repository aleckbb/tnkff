package edu.project3.Printer;

import edu.project3.model.Statistic;

public class BridgePrinter implements Printer {
    private final Printer printer;

    public BridgePrinter(Printer printer) {
        this.printer = printer;
    }

    public BridgePrinter(String printerString) {
        this.printer = switch (printerString) {
            case "markdown" -> new MarkdownPrinter();
            case "adoc" -> new AdocPrinter();
            default -> throw new IllegalStateException("Unexpected value: " + printerString);
        };
    }

    @Override
    public <T> String print(Statistic<T> statistic) {
        return printer.print(statistic);
    }
}
