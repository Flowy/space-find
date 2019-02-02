package com.flowyk.spacefind.entity;

import com.flowyk.spacefind.FurnitureMover;

import java.util.*;

public class Room {


    Set<Position> usablePositions = new TreeSet<>();
    private Set<Position> usedPositions = new TreeSet<>();

    private List<FurniturePosition> furniturePositions = new ArrayList<>();

    public Room(Collection<Position> positions) {
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
        Room modifiedRoom = new Room(usablePositions);
        modifiedRoom.usedPositions.addAll(this.usedPositions);
        modifiedRoom.usedPositions.addAll(usedPositions);
        modifiedRoom.furniturePositions.addAll(this.furniturePositions);
        modifiedRoom.furniturePositions.add(new FurniturePosition(furniture, origin));
        return modifiedRoom;
    }

    public Iterator<Position> usablePositionIterator() {
        return usablePositions.iterator();
    }

    public List<FurniturePosition> getFurniturePositions() {
        return furniturePositions;
    }
}
