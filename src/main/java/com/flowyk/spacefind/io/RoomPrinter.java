package com.flowyk.spacefind.io;

import com.flowyk.spacefind.entity.FurniturePosition;
import com.flowyk.spacefind.entity.Room;

public class RoomPrinter {

    public String print(Room room) {
        StringBuilder builder = new StringBuilder();
        for (FurniturePosition furniturePosition : room.getFurniturePositions()) {
            builder.append(furniturePosition.getFurniture().getCode())
                    .append("(")
                    .append(furniturePosition.getOrigin().getDown())
                    .append(",")
                    .append(furniturePosition.getOrigin().getRight())
                    .append(")")
                    .append(" ");
        }
        builder.deleteCharAt(builder.length() - 1); //removes last space
        return builder.toString();
    }
}
