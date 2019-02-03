package com.flowyk.spacefind.entity;

import com.flowyk.spacefind.FurnitureMover;

import java.util.*;

public class Room {

    private int height;
    private int width;

    private Set<Position> usablePositions = new TreeSet<>();
    private Set<Position> usedPositions = new TreeSet<>();

    private List<FurniturePosition> furniturePositions = new ArrayList<>();

    public Room(int height, int width, Collection<Position> positions) {
        if (Integer.signum(height) < 0) {
            throw new IllegalArgumentException("Height can not be less than zero");
        }
        if (Integer.signum(width) < 0) {
            throw new IllegalArgumentException("Width can not be less than zero");
        }
        this.height = height;
        this.width = width;
        usablePositions.addAll(positions);
    }

    public boolean isUsable(Position position) {
        return usablePositions.contains(position);
    }

    public boolean isEmpty(Position position) {
        return !usedPositions.contains(position);
    }

    /**
     * creates new room, immutable
     * use {@link FurnitureMover} for moving furniture, this method does not check possibility
     */
    public Room put(Furniture furniture, Position origin, List<Position> usedPositions) {
        Room modifiedRoom = new Room(height, width, usablePositions);
        modifiedRoom.usedPositions.addAll(this.usedPositions);
        modifiedRoom.usedPositions.addAll(usedPositions);
        modifiedRoom.furniturePositions.addAll(this.furniturePositions);
        modifiedRoom.furniturePositions.add(new FurniturePosition(furniture, origin));
        return modifiedRoom;
    }

    public Iterator<Position> roomPositionIterator() {
        return new SquareIterator(height, width);
    }

    public List<FurniturePosition> getFurniturePositions() {
        return furniturePositions;
    }

    static class SquareIterator implements Iterator<Position> {

        private int height;
        private int width;

        private int next;

        SquareIterator(int height, int width) {
            this.height = height;
            this.width = width;
        }

        @Override
        public boolean hasNext() {
            return next < (height * width);
        }

        @Override
        public Position next() {
            int down = next / width;
            int right = next % width;
            next += 1;
            return new Position(down, right);
        }
    }
}
