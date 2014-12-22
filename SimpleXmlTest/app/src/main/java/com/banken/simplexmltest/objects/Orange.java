package com.banken.simplexmltest.objects;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

public class Orange implements GameObject {
    @Element
    public double containedJuice;
    private final int id;

    public Orange(@Attribute(name = "id") int id) {
        super();
        this.id = id;
    }

    @Attribute(name = "id")
    @Override
    public int getObjectId() {
        return id;
    }
}