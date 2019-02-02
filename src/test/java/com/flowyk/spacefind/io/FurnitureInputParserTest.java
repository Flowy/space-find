package com.flowyk.spacefind.io;

import com.flowyk.spacefind.entity.Furniture;
import com.flowyk.spacefind.entity.Position;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class FurnitureInputParserTest {

    @Test(expected = RuntimeException.class)
    public void failOnEmptyInput() {
        FurnitureInputParser parser = new FurnitureInputParser();
        parser.input("");
    }

    @Test(expected = RuntimeException.class)
    public void failOnNonSizeFurniture() {
        FurnitureInputParser parser = new FurnitureInputParser();
        parser.input("A0");
    }

    @Test(expected = RuntimeException.class)
    public void failOnWrongOrganization() {
        FurnitureInputParser parser = new FurnitureInputParser();
        parser.input("A2###");
    }

    @Test(expected = RuntimeException.class)
    public void failOnEmptyFurniture() {
        FurnitureInputParser parser = new FurnitureInputParser();
        parser.input("A2..");
    }

    @Test
    public void simpleInput() {
        FurnitureInputParser parser = new FurnitureInputParser();
        parser.input("A1#");
        Collection<Furniture> result = parser.getResult();
        Assert.assertEquals("Result size", 1, result.size());
    }

    @Test
    public void multipleInput() {
        FurnitureInputParser parser = new FurnitureInputParser();
        parser.input("A1#");
        parser.input("B2##..##");
        Collection<Furniture> result = parser.getResult();
        Assert.assertEquals("Result size", 2, result.size());
    }

    @Test
    public void positionParsing() {
        FurnitureInputParser parser = new FurnitureInputParser();
        parser.input("A2#...##");
        Collection<Furniture> result = parser.getResult();
        Assert.assertEquals("Result size", 1, result.size());

        Furniture furniture = result.iterator().next();
        Assert.assertTrue(furniture.getPositions().contains(new Position(0, 0)));
        Assert.assertFalse(furniture.getPositions().contains(new Position(0, 1)));
        Assert.assertFalse(furniture.getPositions().contains(new Position(1, 0)));
        Assert.assertFalse(furniture.getPositions().contains(new Position(1, 1)));
        Assert.assertTrue(furniture.getPositions().contains(new Position(2, 0)));
        Assert.assertTrue(furniture.getPositions().contains(new Position(2, 1)));
    }
}
