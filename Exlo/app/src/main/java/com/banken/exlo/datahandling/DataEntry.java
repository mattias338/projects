package com.banken.exlo.datahandling;

public class DataEntry {
    private final long time;
    private final String type;
    private final String unit;

    public DataEntry(long time, String type, String unit) {
        this.time = time;
        this.type = type;
        this.unit = unit;
    }

    public long getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getUnit() {
        return unit;
    }
}
