package com.banken.simplexmltest.objects;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class Level {
    @ElementList
    public List<GameObject> gameObjects = new ArrayList<GameObject>();
}
