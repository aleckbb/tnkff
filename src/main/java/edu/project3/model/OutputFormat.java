package edu.project3.model;

public enum OutputFormat {
    //    markdown или adoc
    MARKDOWN,
    ADOC;

    public static OutputFormat getFormatFromString(String string) {
        return switch (string) {
            case "markdown" -> OutputFormat.MARKDOWN;
            case "adoc" -> OutputFormat.ADOC;
            default -> throw new IllegalStateException("Unexpected value: " + string);
        };
    }

}
