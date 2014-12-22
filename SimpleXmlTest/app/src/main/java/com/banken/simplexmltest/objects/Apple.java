package com.banken.simplexmltest.objects;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class Apple implements GameObject {
    private final int seedCount;
    private final int id;

    public Apple(@Attribute(name = "id") int id, @Element(name = "seedCount") int seedCount) {
        super();
        this.seedCount = seedCount;
        this.id = id;
    }

    @Element(name = "seedCount")
    public int getSeedCount() {
        return this.seedCount;
    }

    @Attribute(name = "id")
    @Override
    public int getObjectId() {
        return id;
    }
}
