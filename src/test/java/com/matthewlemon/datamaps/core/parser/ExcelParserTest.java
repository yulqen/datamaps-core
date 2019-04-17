package com.matthewlemon.datamaps.core.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.junit.Before;
import org.junit.Test;

import com.matthewlemon.datamaps.core.doubles.InMemoryDatamapGateway;
import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.entities.InMemoryReturn;

public class ExcelParserTest {

	private File testFile;
	private InMemoryDatamapGateway gateway;

	@Before
	public void setUp() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		testFile = new File(classLoader.getResource("files/test_populated_template.xlsx").getFile());
	}

	@Test
	public void checkHashStructure() throws Exception {
		DatamapValue<Boolean> value = new DatamapValue<Boolean>(true);
		assertEquals(true, value.getValue());
		DatamapValue<String> str = new DatamapValue<String>("Tosser");
		assertEquals("Tosser", str.getValue());
	}

	@Test
	public void returnInstantiation() throws Exception {
		InMemoryReturn excelReturn = new InMemoryReturn();
		HashMap<?, ?> data = excelReturn.getData();
		assertNotNull(data);
	}

	@Test
	public void canGetValuesFromCellsInParsedSpreadsheet() throws EncryptedDocumentException, IOException {
		ReturnParser parser = new ReturnParser();
		parser.parse(testFile);
		assertEquals("Test Value 1", parser.getReturn().getCellValue("Test Sheet 1", "B1").getValue());
		assertEquals(12.1, parser.getReturn().getCellValue("Test Sheet 1", "C10").getValue());
		assertEquals(12.0, parser.getReturn().getCellValue("Test Sheet 1", "B10").getValue());
		assertEquals(1436.65, (Double) parser.getReturn().getCellValue("Test Sheet 1", "C13").getValue(), 0.1);
		assertEquals("Formula Result", parser.getReturn().getCellValue("Test Sheet 1", "C15").getValue());
		assertEquals(234.0, parser.getReturn().getCellValue("Test Sheet 2", "D9").getValue());
		assertEquals("23/02/42", parser.getReturn().getCellValue("Test Sheet 2", "D10").getValue());
		assertEquals("1 Jan 2019", parser.getReturn().getCellValue("Test Sheet 2", "D11").getValue());
	}

	@Test
	public void canGetValuesFromCellsUsingLineFromDatamap() throws Exception {
		List<DatamapLine> datamapLines = new ArrayList<>();
		gateway = new InMemoryDatamapGateway();
		gateway.createDatamap("Test Datamap");
		gateway.addLineToDatamap("Test Datamap", "Test Key 1", "Test Sheet 1", "B1");
		gateway.addLineToDatamap("Test Datamap", "Test Key 2", "Test Sheet 1", "B2");
		DatamapLine dml = gateway.getDatamapLineFrom("Test Datamap", "Test Key 1");
		DatamapLine dml1 = gateway.getDatamapLineFrom("Test Datamap", "Test Key 2");

		ReturnParser parser = new ReturnParser();
		parser.parse(testFile);
		assertEquals("Test Value 1", parser.getReturn().getCellValue("Test Sheet 1", dml).getValue());
		assertEquals("Test Value 2", parser.getReturn().getCellValue("Test Sheet 1", dml1).getValue());

	}
}
