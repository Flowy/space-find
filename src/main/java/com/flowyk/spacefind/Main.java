package com.flowyk.spacefind;

import com.flowyk.spacefind.entity.Furniture;
import com.flowyk.spacefind.entity.Room;
import com.flowyk.spacefind.io.FurnitureInputParser;
import com.flowyk.spacefind.io.RoomInputParser;
import com.flowyk.spacefind.io.RoomPrinter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String fileName = "input";
        if (args != null && args.length == 1) {
            fileName = args[0];
        }
        System.out.println("Using input from file: " + fileName);
        Path path = Paths.get(fileName);
        List<String> lines;
        try {
            lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<List<String>> chunks = splitLines(lines);

        Room room = parseRoom(chunks.get(0));
        List<Furniture> furnitures = parseFurnitures(chunks.get(1));

        List<Room> solutions = new BreadthFirstSearch().searchSolutions(room, furnitures);

        List<String> outputLines = new ArrayList<>();
        for (Room solution : solutions) {
            outputLines.add(new RoomPrinter().print(solution));
        }

        Path output;
        try {
            output = Files.createTempFile("output", "");
            Files.write(output, outputLines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Solutions are written in " + output.toAbsolutePath());
    }

    private static List<Furniture> parseFurnitures(List<String> chunk) {
        FurnitureInputParser parser = new FurnitureInputParser();
        for (String line : chunk) {
            parser.input(line);
        }
        return parser.getResult();
    }

    private static Room parseRoom(List<String> chunk) {
        RoomInputParser parser = new RoomInputParser();
        for (String line : chunk) {
            parser.input(line);
        }
        return parser.getResult();
    }

    private static List<List<String>> splitLines(List<String> lines) {
        List<List<String>> result = new ArrayList<>();
        List<String> actualChunk = new ArrayList<>();
        for (String line : lines) {
            if (line.isEmpty()) {
                result.add(actualChunk);
                actualChunk = new ArrayList<>();
            } else {
                actualChunk.add(line);
            }
        }
        result.add(actualChunk);
        return result;
    }
}
