package com.flowyk.spacefind;

import com.flowyk.spacefind.entity.Furniture;
import com.flowyk.spacefind.entity.Position;
import com.flowyk.spacefind.entity.Room;

import java.util.*;

public class BreadthFirstSearch {

    List<Room> searchSolutions(Room emptyRoom, Collection<Furniture> furnitures) {
        List<Room> actualLayer;
        List<Room> nextLayer = Collections.singletonList(emptyRoom);
        for (Furniture furniture : furnitures) {
            actualLayer = nextLayer;
            nextLayer = new ArrayList<>();
            for (Room room : actualLayer) {
                nextLayer.addAll(searchOneLayer(room, furniture));
            }
        }
        return nextLayer;
    }

    List<Room> searchOneLayer(Room room, Furniture furniture) {
        Iterator<Position> positionIterator = room.roomPositionIterator();
        FurnitureMover mover = new FurnitureMover();
        List<Room> solutions = new ArrayList<>();
        while (positionIterator.hasNext()) {
            Position usablePosition = positionIterator.next();
            Room solution = mover.moveInto(room, furniture, usablePosition);
            if (solution != null) {
                solutions.add(solution);
            }
        }
        return solutions;
    }
}
