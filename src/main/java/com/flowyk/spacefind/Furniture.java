package com.flowyk.spacefind;

import com.flowyk.spacefind.coordinate.Position;

import java.util.List;

public class Furniture {

    private final String code;
    private final List<Position> positions;

    public Furniture(String code, List<Position> positions) {
        this.code = code;
        this.positions = positions;
    }

    public boolean isOn(Position position) {
        return positions.contains(position);
    }
}
