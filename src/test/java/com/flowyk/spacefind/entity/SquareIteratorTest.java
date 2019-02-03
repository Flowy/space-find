package com.flowyk.spacefind.entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SquareIteratorTest {

    @Test
    public void oneRow() {
        Room.SquareIterator iterator = new Room.SquareIterator(1, 4);
        List<Position> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        Assert.assertEquals("Number of positions", 4, result.size());
    }

    @Test
    public void twoRows() {
        Room.SquareIterator iterator = new Room.SquareIterator(2, 4);
        List<Position> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        Assert.assertEquals("Number of positions", 8, result.size());
    }
}
