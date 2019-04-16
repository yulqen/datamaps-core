package com.matthewlemon.datamaps.core.gateway;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.matthewlemon.datamaps.core.TestSetup;
import com.matthewlemon.datamaps.core.doubles.InMemoryDatamapGateway;
import com.matthewlemon.datamaps.core.entities.CSVFile;
import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;
import com.matthewlemon.datamaps.core.parser.DatamapType;

public class InMemoryDatamapShould {

    private static InMemoryDatamapGateway gateway;
    private static File testFile;
    private static CSVFile csvFile;

    @BeforeClass
    public static void setUpClass() {
        TestSetup.setupContext();
    }

    @Before
    public void setUp() throws DuplicateDatamapException {
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
                "Test Key", "Sheet 1", "B1", new DatamapType());
        List<DatamapLine> datamaplines = gateway.getDataLinesFor("Test Datamap");
        assertEquals("Test Key", datamaplines.get(0).getKey());
        // manual delete of that line
    }

	@Test
    public void useGatewayToAddToDatamapWithCSV() {
        DatamapLine dml = new DatamapLine("Test Key 1", "Test Sheet 1",
                "Test CellRef 1", new DatamapType());
        gateway.addDataToDatamapWithCSV("Test Datamap", csvFile);
        assertEquals(dml.getKey(), gateway.getDatamap("Test Datamap")
                .getDatamapLines().get(0).getKey());
    }

    @Test
    public void addDataToDatamapFromCSV() throws IOException {
        Datamap datamap = gateway.getDatamap("Test Datamap");
        datamap.readCSV(testFile);
        assertEquals("Test Key 1", datamap.getDataKeyForLine(0));
        assertEquals("Test Key 2", datamap.getDataKeyForLine(1));
    }
    
    @Test(expected=DuplicateDatamapException.class)
	public void rejectDatamapWithSameName() throws Exception {
		@SuppressWarnings("unused")
		Datamap datamap = gateway.createDatamap("Test Datamap");
		@SuppressWarnings("unused")
		Datamap datamap2 = gateway.createDatamap("Test Datamap");
	}
}
