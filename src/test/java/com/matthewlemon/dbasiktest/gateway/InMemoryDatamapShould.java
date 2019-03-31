package com.matthewlemon.dbasiktest.gateway;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.matthewlemon.dbasiktest.TestSetup;
import com.matthewlemon.dbasiktest.doubles.InMemoryDatamapGateway;
import com.matthewlemon.dbasiktest.entities.CSVFile;
import com.matthewlemon.dbasiktest.entities.Datamap;
import com.matthewlemon.dbasiktest.entities.DatamapLine;

public class InMemoryDatamapShould {

    private static InMemoryDatamapGateway gateway;
    private static File testFile;
    private static CSVFile csvFile;

    @BeforeClass
    public static void setUpClass() {
        TestSetup.setupContext();
    }

    @Before
    public void setUp() {
    	ClassLoader classLoader = getClass().getClassLoader();
        testFile = new File(classLoader.getResource("files/test.csv")
        		.getFile());
        csvFile = new CSVFile(testFile);
        gateway = new InMemoryDatamapGateway();
        gateway.createDatamap("Test Datamap");
    }

    @After
    public void clearDatamapLines() {
        gateway.deleteAllLinesIn("Test Datamap");
    }

    @Test
    public void addLineToDatamap() {
        gateway.addLineToDatamap("Test Datamap",
                "Test Key", "Sheet 1", "B1");
        List<DatamapLine> datamaplines = gateway.getDataLinesFor("Test Datamap");
        assertEquals(datamaplines.get(0).getKey(), "Test Key");
        // manual delete of that line
    }


    @Test
    public void useGatewayToAddToDatamapWithCSV() {
        DatamapLine dml = new DatamapLine("Test Key 1", "Test Sheet 1",
                "Test CellRef 1");
        gateway.addDataToDatamapWithCSV("Test Datamap", csvFile);
        assertEquals(gateway.getDatamap("Test Datamap")
                .getDatamapLines().get(0).getKey(), dml.getKey());
    }

    @Test
    public void addDataToDatamapFromCSV() throws IOException {
        Datamap datamap = gateway.getDatamap("Test Datamap");
        datamap.readCSV(testFile);
        assertEquals(datamap.getDatamapLines().get(0).getKey(), "Test Key 1");
        assertEquals(datamap.getDatamapLines().get(1).getKey(), "Test Key 2");
    }
}
