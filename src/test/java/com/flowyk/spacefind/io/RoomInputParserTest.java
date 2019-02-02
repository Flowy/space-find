package com.flowyk.spacefind.io;

import com.flowyk.spacefind.entity.Position;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RoomInputParserTest {

    @Test
    public void initialLine() {
        RoomInputParser parser = new RoomInputParser();
        parser.input("0,0");

        Assert.assertEquals("Input height", Integer.valueOf(0), parser.height);
        Assert.assertEquals("Input width", Integer.valueOf(0), parser.width);
    }

    @Test
    public void occupancyLine() {
        RoomInputParser parser = new RoomInputParser();
        parser.input("1,6");
        parser.input("..###.");

        List<Position> expected = new ArrayList<>();
        expected.add(new Position(0, 2));
        expected.add(new Position(0, 3));
        expected.add(new Position(0, 4));
        Assert.assertEquals(expected, parser.usable);
    }

    @Test(expected = RuntimeException.class)
    public void failsOnManyLines() {
        RoomInputParser parser = new RoomInputParser();
        parser.input("0,6");
        parser.input("..###.");
    }

    @Test(expected = RuntimeException.class)
    public void failsOnManySymbols() {
        RoomInputParser parser = new RoomInputParser();
        parser.input("1,5");
        parser.input("..###.");
    }

    @Test(expected = RuntimeException.class)
    public void failsOnFewSymbols() {
        RoomInputParser parser = new RoomInputParser();
        parser.input("1,7");
        parser.input("..###.");
    }
}
