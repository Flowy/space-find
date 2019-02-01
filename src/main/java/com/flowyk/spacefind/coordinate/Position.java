package com.flowyk.spacefind.coordinate;

import java.util.Objects;

public class Position {
    int down;
    int right;

    public Position(int down, int right) {
        this.down = down;
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return down == position.down &&
                right == position.right;
    }

    @Override
    public int hashCode() {
        return Objects.hash(down, right);
    }
}
