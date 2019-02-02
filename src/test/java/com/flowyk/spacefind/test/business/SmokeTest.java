package com.flowyk.spacefind.test.business;

import com.flowyk.spacefind.Furniture;
import com.flowyk.spacefind.Room;
import com.flowyk.spacefind.RoomInputParser;
import com.flowyk.spacefind.SpacePlanner;
import com.flowyk.spacefind.io.FurnitureInputParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class SmokeTest {

    @Test
    public void squareRoomWithOneFurniture() {
        RoomInputParser roomParser = new RoomInputParser();
        roomParser.input("1,1");
        roomParser.input("#");
        FurnitureInputParser furnitureParser = new FurnitureInputParser();
        furnitureParser.input("A1#");

        Collection<Furniture> furnitures = furnitureParser.getResult();
        Assert.assertEquals("Number of parsed furnitures", 1, furnitures.size());
        Furniture furniture = furnitures.iterator().next();
        SpacePlanner planner = SpacePlanner.plan(roomParser.getResult(), furniture);

        Collection<Room> solutions = new ArrayList<>();
        Room actualSolution;
        while ((actualSolution = planner.nextSolution()) != null) {
            solutions.add(actualSolution);
        }
        Assert.assertEquals("Number of solutions", 1, solutions.size());

    }
}
