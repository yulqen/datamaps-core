package com.matthewlemon.dbasiktest.gateway;

import com.matthewlemon.dbasiktest.TestSetup;
import com.matthewlemon.dbasiktest.doubles.InMemoryDatamapGateway;
import com.matthewlemon.dbasiktest.entities.CSVFile;
import com.matthewlemon.dbasiktest.entities.Datamap;
import com.matthewlemon.dbasiktest.entities.DatamapLine;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class InMemoryDatamapShould {

    private static InMemoryDatamapGateway gateway;
    private static File testFile;

    @Mock
    private static CSVFile csvFile;

    @BeforeClass
    public static void setUpClass() {
        TestSetup.setupContext();
        csvFile = new CSVFile("test.csv");
    }

    @Before
    public void setUp() {
        testFile = new File("/home/lemon/Desktop/test.csv");
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

        when(csvFile.getFile()).thenReturn(testFile);

        gateway.addDataToDatamapWithCSV("Test Datamap", csvFile);
        assertEquals(gateway.getDatamap("Test Datamap")
                .getDatamapLines().get(0).getKey(), dml.getKey());
    }

    @Test
    public void addDataToDatamapFromCSV() throws IOException {
        File testFile = new File("/home/lemon/Desktop/test.csv");
        Datamap datamap = gateway.getDatamap("Test Datamap");
        datamap.readCSV(testFile);
        assertEquals(datamap.getDatamapLines().get(0).getKey(), "Test Key 1");
        assertEquals(datamap.getDatamapLines().get(1).getKey(), "Test Key 2");
    }
}
