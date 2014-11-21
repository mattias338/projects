package com.banken.exlo;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private static final List<String> types;
    private static final List<String> units;

    static {
        types = new ArrayList<String>();
        types.add("Weight");
        types.add("Height");
        types.add("Push ups");

        units = new ArrayList<String>();
        units.add("kg");
        units.add("seconds");
        units.add("repetitions");
    }

    public static List<String> getTypes() {
        return types;
    }

    public static List<String> getUnits() {
        return units;
    }
}
