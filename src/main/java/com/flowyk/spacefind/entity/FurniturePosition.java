package com.flowyk.spacefind.entity;

public class FurniturePosition {
    private Position origin;
    private Furniture furniture;

    public FurniturePosition(Furniture furniture, Position origin) {
        this.origin = origin;
        this.furniture = furniture;
    }

    public Position getOrigin() {
        return origin;
    }

    public Furniture getFurniture() {
        return furniture;
    }
}
