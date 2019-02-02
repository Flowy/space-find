package com.flowyk.spacefind.entity;

import java.util.Objects;

public class Position implements Comparable<Position> {
    int down;
    int right;

    public Position(int down, int right) {
        this.down = down;
        this.right = right;
    }

    public Position translateTo(Position origin) {
        return new Position(down + origin.down, right + origin.right);
    }

    public int getDown() {
        return down;
    }

    public int getRight() {
        return right;
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

    @Override
    public int compareTo(Position o) {
        int xDiff = Integer.compare(this.down, o.down);
        if (xDiff != 0) {
            return xDiff;
        }
        return Integer.compare(this.right, o.right);
    }
}
