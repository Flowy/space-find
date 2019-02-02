package com.flowyk.spacefind.entity;

import java.util.List;

public class Furniture {

    private final String code;
    final List<Position> positions;

    public Furniture(String code, List<Position> positions) {
        this.code = code;
        this.positions = positions;
    }

    public String getCode() {
        return code;
    }

    public List<Position> getPositions() {
        return positions;
    }
}
