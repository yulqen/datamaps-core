package com.matthewlemon.datamaps.core.gateway;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.matthewlemon.datamaps.core.TestSetup;
import com.matthewlemon.datamaps.core.doubles.InMemoryDatamapGateway;
import com.matthewlemon.datamaps.core.entities.CSVFile;
import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.exceptions.DatamapLineNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DatamapNotFoundException;
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
		testFile = new File(classLoader.getResource("files/test.csv").getFile());
		csvFile = new CSVFile(testFile);
		gateway = new InMemoryDatamapGateway();
		gateway.createDatamap("Test Datamap");
	}

	@After
	public void clearDatamapLines() {
		gateway.deleteAllLinesIn("Test Datamap");
	}

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void addLineToDatamap() throws DatamapNotFoundException, DatamapLineNotFoundException {
		gateway.addLineToDatamap("Test Datamap", "Test Key", "Sheet 1", "B1", new DatamapType());
		List<DatamapLine> datamaplines = gateway.getDataLinesFor("Test Datamap");
		assertEquals("Test Key", datamaplines.get(0).getKey());
		// alternative way of getting the key:
		assertEquals("Test Key", gateway.getDatamapLineFrom("Test Datamap", "Test Key").getKey());
	}

	@Test
	public void useGatewayToAddToDatamapWithCSV() throws DatamapNotFoundException {
		DatamapLine dml = new DatamapLine("Test Key 1", "Test Sheet 1", "Test CellRef 1", new DatamapType());
		gateway.addDataToDatamapWithCSV("Test Datamap", csvFile);
		assertEquals(dml.getKey(), gateway.getDatamap("Test Datamap").getDatamapLines().get(0).getKey());
	}

	@Test
	public void addDataToDatamapFromCSV() throws IOException, DatamapNotFoundException {
		Datamap datamap = gateway.getDatamap("Test Datamap");
		datamap.readCSV(testFile);
		assertEquals("Test Key 1", datamap.getDataKeyForLine(0));
		assertEquals("Test Key 2", datamap.getDataKeyForLine(1));
	}

	@Test(expected = DuplicateDatamapException.class)
	public void rejectDatamapWithSameName() throws Exception {
		@SuppressWarnings("unused")
		Datamap datamap = gateway.createDatamap("Test Datamap");
		@SuppressWarnings("unused")
		Datamap datamap2 = gateway.createDatamap("Test Datamap");
	}

	@Test
	public void canGetSingleDatamapLineByQueryingItsSheetAndKeyValues()
			throws DuplicateDatamapException, DatamapNotFoundException, DatamapLineNotFoundException {
		@SuppressWarnings("unused")
		Datamap datamap = gateway.createDatamap("Test Datamap 2"); // create a new Datamap object
		gateway.addLineToDatamap("Test Datamap 2", "Test Key 1", "Test Sheet 1", "B1");
		gateway.addLineToDatamap("Test Datamap 2", "Test Key 2", "Test Sheet 1", "B2");
		DatamapLine dml = gateway.getDatamapLineFrom("Test Datamap 2", "Test Key 1");
		assertEquals("Test Key 1", dml.getKey());
		assertEquals("Test Sheet 1", dml.getSheetName());
		assertEquals("B1", dml.getCellRef());
	}

	@Test
	public void throwExceptionWhenDatamapDoesntExist() throws Exception {
		thrown.expect(DatamapNotFoundException.class);
		thrown.expectMessage("Datamap Test Datamap No Exist cannot be found.");
		@SuppressWarnings("unused")
		Datamap datamap = gateway.getDatamap("Test Datamap No Exist");
	}
}
