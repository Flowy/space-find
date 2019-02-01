package com.flowyk.spacefind.test.business;

import com.flowyk.spacefind.Furniture;
import com.flowyk.spacefind.Room;
import com.flowyk.spacefind.RoomInputParser;
import com.flowyk.spacefind.io.FurnitureInputParser;
import com.flowyk.spacefind.test.category.RegressionTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Collection;

@Category(RegressionTest.class)
public class InputTest {

    @Test
    public void exampleRoomCanBeParsed() {
        RoomInputParser parser = new RoomInputParser();
        parser.input("");
        parser.input("5,6");
        parser.input("..###.");
        parser.input(".####.");
        parser.input("######");
        parser.input("######");
        parser.input("...###");

        Room room = parser.getResult();
    }

    @Test
    public void exampleFurnitureCanBeParsed() {
        FurnitureInputParser parser = new FurnitureInputParser();
        parser.input("A2####");
        parser.input("B3.#.###.#.");

        Collection<Furniture> furniture = parser.getResult();
    }
}
