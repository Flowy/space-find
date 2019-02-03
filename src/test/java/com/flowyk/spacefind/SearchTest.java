package com.flowyk.spacefind;

import com.flowyk.spacefind.entity.Furniture;
import com.flowyk.spacefind.entity.Position;
import com.flowyk.spacefind.entity.Room;
import com.flowyk.spacefind.io.RoomPrinter;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class SearchTest {

    @Test
    public void oneLayerAllPossible() {
        Room room = new Room(2, 2, Arrays.asList(
                new Position(0, 0),
                new Position(0, 1),
                new Position(1, 0),
                new Position(1, 1)
        ));
        Furniture furniture = new Furniture("A", Arrays.asList(
                new Position(0, 0)
        ));

        List<Room> possibleSolutions = new BreadthFirstSearch().searchOneLayer(room, furniture);
        Assert.assertEquals("Number of solutions", 4, possibleSolutions.size());
    }

    @Test
    public void twoLayers() {
        Room room = new Room(2, 2, Arrays.asList(
                new Position(0, 0),
                new Position(0, 1),
                new Position(1, 0),
                new Position(1, 1)
        ));
        Furniture furnitureA = new Furniture("A", Arrays.asList(
                new Position(0, 0)
        ));
        Furniture furnitureB = new Furniture("B", Arrays.asList(
                new Position(0, 0)
        ));
        List<Room> possibleSolutions = new BreadthFirstSearch()
                .searchSolutions(room, Arrays.asList(furnitureA, furnitureB));

        for (Room solution : possibleSolutions) {
            System.out.println(new RoomPrinter().print(solution));
        }
        Assert.assertEquals("Number of solutions", 12, possibleSolutions.size());
    }
}
