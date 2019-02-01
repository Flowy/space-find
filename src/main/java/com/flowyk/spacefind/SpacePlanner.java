package com.flowyk.spacefind;

import com.flowyk.spacefind.coordinate.Position;

public class SpacePlanner {

    private Room room;
    private Furniture furniture;

    private Position lastPosition;

    public static SpacePlanner plan(Room room, Furniture furniture) {
        SpacePlanner instance = new SpacePlanner();
        instance.room = room;
        instance.furniture = furniture;
        return instance;
    }

    public Room nextSolution() {
        return null;
    }
}
