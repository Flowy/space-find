package com.flowyk.spacefind;

import com.flowyk.spacefind.coordinate.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomInputParser {

    private static Pattern initialPattern = Pattern.compile("(?<height>\\d+),(?<width>\\d+)");
    private static Pattern occupancyPattern = Pattern.compile("([\\\\.#])");

    Room room;
    Integer height;
    Integer width;

    int loadedDepth = 0;

    Collection<Position> occupied = new ArrayList<>();

    public void input(String line) {
        if (width == null && height == null) {
            parseInitial(line);
        } else {
            parseOccupancy(line);
        }
    }

    void parseInitial(String line) {
        Matcher initial = initialPattern.matcher(line);
        if (!initial.matches()) {
            throw new IllegalArgumentException("Initial input '" + line + "' for room can not be parsed");
        }
        height = Integer.valueOf(initial.group("height"));
        width = Integer.valueOf(initial.group("width"));
    }

    void parseOccupancy(String line) {
        Matcher occupancy = occupancyPattern.matcher(line);

        if (loadedDepth >= height) {
            throw new IllegalArgumentException("Room input '" + line + "' is out of bounds for this room, expected height: " + height);
        }

        int loadedPosition = 0;
        while (occupancy.find()) {
            String symbol = occupancy.group(1);
            if ("#".equals(symbol)) {
                occupied.add(new Position(loadedDepth, loadedPosition));
            }
            loadedPosition++; //will end with one more position
        }

        if (loadedPosition < width) {
            throw new IllegalArgumentException("Room input '" + line + "' is too short, expected: " + width);
        }

        if (loadedPosition > width) {
            throw new IllegalArgumentException("Room input '" + line + "' is too long, expected: " + width);
        }

        loadedDepth += 1;
    }

    public Room getResult() {
        //UPGRADE: validate number of lines to
        return room;
    }
}
