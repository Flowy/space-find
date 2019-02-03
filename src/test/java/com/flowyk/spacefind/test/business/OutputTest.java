package com.flowyk.spacefind.test.business;

import com.flowyk.spacefind.entity.Furniture;
import com.flowyk.spacefind.entity.Position;
import com.flowyk.spacefind.entity.Room;
import com.flowyk.spacefind.io.RoomPrinter;
import com.flowyk.spacefind.test.category.RegressionTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Category(RegressionTest.class)
public class OutputTest {


    @Test
    public void emptyRoom() {
        RoomPrinter printer = new RoomPrinter();

        Room room = new Room(Arrays.asList(
                new Position(0, 1),
                new Position(1, 1),
                new Position(4, 6)
        ));
        String output = printer.print(room);
        Assert.assertEquals("Response", "", output);
    }

    @Test
    public void existingSolution() {
        RoomPrinter printer = new RoomPrinter();

        Room room = new Room(Arrays.asList(
                new Position(0, 1),
                new Position(1, 1),
                new Position(4, 6)
        ));
        room = room.put(new Furniture("A", Arrays.asList(new Position(0, 0))),
                new Position(0, 1),
                Arrays.asList(new Position(0, 1)));
        String output = printer.print(room);
        Pattern requiredPattern = Pattern.compile("^(\\w\\(\\d+,\\d+\\) )*(\\w\\(\\d+,\\d+\\))$");
        Matcher matcher = requiredPattern.matcher(output);
        Assert.assertEquals("Output valid to pattern", true, matcher.matches());
    }
}
