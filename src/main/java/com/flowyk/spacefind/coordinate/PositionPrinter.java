package com.flowyk.spacefind.coordinate;

public class PositionPrinter {

    public String print(Position position) {
        return "(" + position.down + "," + position.right + ")";
    }
}
