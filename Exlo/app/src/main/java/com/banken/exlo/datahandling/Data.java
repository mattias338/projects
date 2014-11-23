package com.banken.exlo.datahandling;

import java.util.List;

public interface Data {
    public void addEntry(DataEntry dataEntry);

    public List<DataEntry> getEntries();
}
