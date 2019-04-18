package com.matthewlemon.datamaps.core.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.matthewlemon.datamaps.core.doubles.InMemoryDatamapGateway;
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
		InMemoryReturn myReturn = new InMemoryReturn("Q1 Return");
		ReturnParser parser = new ReturnParser(myReturn);
		parser.parse(testFile);
		assertEquals("Test Value 1", parser.getCellValueFromSheet("Test Sheet 1", "B1"));
		assertEquals(12.1, parser.getCellValueFromSheet("Test Sheet 1", "C10"));
		assertEquals(12.0, parser.getCellValueFromSheet("Test Sheet 1", "B10"));
		assertEquals(1436.65, (Double)parser.getCellValueFromSheet("Test Sheet 1", "C13"), 0.1);
		assertEquals("Formula Result", parser.getCellValueFromSheet("Test Sheet 1", "C15"));
		assertEquals(234.0, parser.getCellValueFromSheet("Test Sheet 2", "D9"));
		assertEquals("23/02/42", parser.getCellValueFromSheet("Test Sheet 2", "D10"));
		assertEquals("1 Jan 2019", parser.getCellValueFromSheet("Test Sheet 2", "D11"));
	}

	@Test
	public void canGetValuesFromCellsUsingLineFromDatamap() throws Exception {
		gateway = new InMemoryDatamapGateway();
		gateway.createDatamap("Test Datamap");
		gateway.addLineToDatamap("Test Datamap", "Test Key 1", "Test Sheet 1", "B1");
		gateway.addLineToDatamap("Test Datamap", "Test Key 2", "Test Sheet 1", "B2");
		DatamapLine dml = gateway.getDatamapLineFrom("Test Datamap", "Test Key 1");
		DatamapLine dml1 = gateway.getDatamapLineFrom("Test Datamap", "Test Key 2");

		ReturnParser parser = new ReturnParser();
		parser.parse(testFile);
		assertEquals("Test Value 1", parser.getCellValueFromSheet("Test Sheet 1", dml));
		assertEquals("Test Value 2", parser.getCellValueFromSheet("Test Sheet 1", dml1));

	}

	@Test
	public void canGetValuesFromCellsUsingParserMethod() throws Exception {
		gateway = new InMemoryDatamapGateway();
		gateway.createDatamap("Test Datamap 3");
		gateway.addLineToDatamap("Test Datamap 3", "Test Key 1", "Test Sheet 1", "B1");
		DatamapLine dml = gateway.getDatamapLineFrom("Test Datamap 3", "Test Key 1");

		ReturnParser parser = new ReturnParser();
		parser.parse(testFile);
		// two ways of getting the value
		assertEquals("Test Value 1", parser.getCellValueFromSheet("Test Sheet 1", dml));
		assertEquals("Test Value 1", parser.getCellValueFromSheet("Test Sheet 1", "B1"));
	}
}
