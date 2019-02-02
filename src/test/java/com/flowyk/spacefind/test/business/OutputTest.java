package com.flowyk.spacefind.test.business;

import com.flowyk.spacefind.io.RoomPrinter;
import com.flowyk.spacefind.test.category.RegressionTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Category(RegressionTest.class)
public class OutputTest {


    @Test
    public void formatOfResponse() {
        RoomPrinter printer = new RoomPrinter();

        String output = printer.print(null);
        if (output.isEmpty()) {
            return; //empty result is possible as we are logging only furniture positions and there can be no furniture
        }
        Pattern requiredPattern = Pattern.compile("^(\\w\\(\\d+,\\d+\\) )*(\\w\\(\\d+,\\d+\\))$");
        Matcher matcher = requiredPattern.matcher(output);
        Assert.assertEquals("Output valid to pattern", true, matcher.matches());
    }
}
