package com.flowyk.spacefind;

import com.flowyk.spacefind.entity.Furniture;
import com.flowyk.spacefind.entity.Position;
import com.flowyk.spacefind.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class FurnitureMover {

    public Room moveInto(Room room, Furniture furniture, Position furnitureOrigin) {
        List<Position> roomPositions = calculatePositions(furniture, furnitureOrigin);
        if (isPossible(room, roomPositions)) {
            return room.put(furniture, furnitureOrigin, roomPositions);
        } else {
            return null;
        }
    }

    List<Position> calculatePositions(Furniture furniture, Position origin) {
        List<Position> roomPositions = new ArrayList<>();
        for (Position position : furniture.getPositions()) {
            roomPositions.add(position.translateTo(origin));
        }
        return roomPositions;
    }

    public boolean isPossible(Room room, List<Position> roomPositions) {
        for (Position roomPosition : roomPositions) {
            if (!room.isUsable(roomPosition) || !room.isEmpty(roomPosition)) {
                return false;
            }
        }
        return true;
    }

}
