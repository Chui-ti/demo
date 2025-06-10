package org.example;

public class GenerationReport {
    private String message;
    private int records;

    public GenerationReport(String message, int records) {
        this.message = message;
        this.records = records;
    }

    public String getMessage() {
        return message;
    }

    public int getRecords() {
        return records;
    }
}
