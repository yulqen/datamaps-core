package com.matthewlemon.dbasiktest.gateway;

import com.matthewlemon.dbasiktest.TestSetup;
import com.matthewlemon.dbasiktest.doubles.InMemoryDatamapGateway;
import com.matthewlemon.dbasiktest.entities.DatamapLine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InMemoryDatamapShould {

    @Test
    public void setUp() {
        TestSetup.setupContext();
    }

    @Test
    public void testListGetter() {
        List<String> list = new ArrayList();
        list.add("tits");
        assertEquals(list.get(0), "tits");
    }


    @Test
    public void addLineToDatamap() {
        InMemoryDatamapGateway gateway = new InMemoryDatamapGateway();
        gateway.createDatamap("Test Datamap");
        gateway.addLineToDatamap("Test Datamap",
                "Test Key", "Sheet 1", "B1");
        gateway.getDatamap("Test Datamap");
        List<DatamapLine> datamaplines = gateway.getDataLinesFor("Test Datamap");
        assertEquals(datamaplines.get(0).getKey(), "Test Key");
    }
}
