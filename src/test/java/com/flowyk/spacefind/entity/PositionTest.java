package com.flowyk.spacefind.entity;

import org.junit.Assert;
import org.junit.Test;

public class PositionTest {

    @Test
    public void translation() {
        Position original = new Position(0, 0);
        Position origin = new Position(1, 1);
        Position result = original.translateTo(origin);

        Assert.assertEquals(new Position(1, 1), result);
    }
}
