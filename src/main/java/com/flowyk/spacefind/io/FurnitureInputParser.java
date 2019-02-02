package com.flowyk.spacefind.io;

import com.flowyk.spacefind.entity.Furniture;
import com.flowyk.spacefind.entity.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FurnitureInputParser {

    private static Pattern furniturePattern = Pattern.compile("(?<code>\\w)(?<width>\\d)(?<organization>[\\\\.#]+)");

    List<Furniture> furnitures = new ArrayList<>();

    public void input(String line) {
        Matcher matcher = furniturePattern.matcher(line);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Line '" + line + "' can not be parsed as furniture");
        }

        int width = Integer.valueOf(matcher.group("width"));
        String organization = matcher.group("organization");

        if (organization.length() % width != 0) {
            throw new IllegalArgumentException("Line '" + line + "' contains wrong number of places");
        }

        int height = organization.length() / width;

        List<Position> furniturePositions = new ArrayList<>();
        for (int actualDepth = 0; actualDepth < height; actualDepth++) {
            for (int actualPosition = 0; actualPosition < width; actualPosition++) {
                char symbol = organization.charAt((actualDepth * width) + actualPosition);
                if (Objects.equals('#', symbol)) {
                    Position position = new Position(actualDepth, actualPosition);
                    furniturePositions.add(position);
                }
            }
        }

        if (furniturePositions.isEmpty()) {
            throw new IllegalArgumentException("Line '" + line + "' contains furniture without used space");
        }

        Furniture furniture = new Furniture(matcher.group("code"), furniturePositions);
        furnitures.add(furniture);

    }

    public Collection<Furniture> getResult() {
        return furnitures;
    }
}
