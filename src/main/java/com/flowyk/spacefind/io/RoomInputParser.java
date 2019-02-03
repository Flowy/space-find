package com.flowyk.spacefind.io;

import com.flowyk.spacefind.entity.Position;
import com.flowyk.spacefind.entity.Room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RoomInputParser {

    private static Pattern initialPattern = Pattern.compile("(?<height>\\d+),(?<width>\\d+)");
    private static Pattern designPattern = Pattern.compile("([\\\\.#])");

    Room room;
    Integer height;
    Integer width;

    int loadedDepth = 0;

    Collection<Position> usable = new ArrayList<>();

    public void input(String line) {
        if (width == null && height == null) {
            parseInitial(line);
        } else {
            parseDesign(line);
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

    void parseDesign(String line) {
        Matcher usableLine = designPattern.matcher(line);

        if (loadedDepth >= height) {
            throw new IllegalArgumentException("Room input '" + line + "' is out of bounds for this room, expected height: " + height);
        }

        int loadedPosition = 0;
        while (usableLine.find()) {
            String symbol = usableLine.group(1);
            if ("#".equals(symbol)) {
                usable.add(new Position(loadedDepth, loadedPosition));
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
        return new Room(usable);
    }
}
