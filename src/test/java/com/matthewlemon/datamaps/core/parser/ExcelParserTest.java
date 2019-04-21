package com.matthewlemon.datamaps.core.parser;

import static com.matthewlemon.datamaps.core.parser.DatamapLineType.DATE;
import static com.matthewlemon.datamaps.core.parser.DatamapLineType.NUMERIC;
import static com.matthewlemon.datamaps.core.parser.DatamapLineType.TEXT;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.entities.InMemoryReturn;
import com.matthewlemon.datamaps.core.exceptions.CellValueNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.IncorrectCellTypeException;
import com.matthewlemon.datamaps.core.gateways.InMemoryDatamapGateway;

public class ExcelParserTest {

	private File testFile;
	private InMemoryDatamapGateway gateway;
	private InMemoryReturn myReturn;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		testFile = new File(classLoader.getResource("files/test_populated_template.xlsx").getFile());
		myReturn = new InMemoryReturn("Test Return");
	}
	
	@Test
	public void canGetValuesFromCellsInParsedSpreadsheet()
			throws EncryptedDocumentException, IOException, CellValueNotFoundException {
		ReturnParser parser = new ReturnParser(this.myReturn);
		parser.parse(testFile);
		assertEquals("Test Value 1", parser.getCellValueFromSheet("Test Sheet 1", "B1"));
		assertEquals(12.1, parser.getCellValueFromSheet("Test Sheet 1", "C10"));
		assertEquals(12.0, parser.getCellValueFromSheet("Test Sheet 1", "B10"));
		assertEquals(1436.65, (Double) parser.getCellValueFromSheet("Test Sheet 1", "C13"), 0.1);
		assertEquals("Formula Result", parser.getCellValueFromSheet("Test Sheet 1", "C15"));
		assertEquals(234.0, parser.getCellValueFromSheet("Test Sheet 2", "D9"));
		assertEquals(new GregorianCalendar(2042, 1, 23), parser.getCellValueFromSheet("Test Sheet 2", "D10"));
		assertEquals(new GregorianCalendar(2019, 0, 1), parser.getCellValueFromSheet("Test Sheet 2", "D11"));
	}

	@Test
	public void canGetValuesFromCellsUsingLineFromDatamap() throws Exception {
		gateway = new InMemoryDatamapGateway();
		gateway.createDatamap("Test Datamap");
		gateway.addLineToDatamap("Test Datamap", "Test Key 1", "Test Sheet 1", "B1", TEXT);
		gateway.addLineToDatamap("Test Datamap", "Test Key 2", "Test Sheet 1", "B2", TEXT);
		DatamapLine dml = gateway.getDatamapLineFrom("Test Datamap", "Test Key 1");
		DatamapLine dml1 = gateway.getDatamapLineFrom("Test Datamap", "Test Key 2");

		ReturnParser parser = new ReturnParser(this.myReturn);
		parser.parse(testFile);
		assertEquals("Test Value 1", parser.getCellValueFromSheet("Test Sheet 1", dml));
		assertEquals("Test Value 2", parser.getCellValueFromSheet("Test Sheet 1", dml1));

	}

	@Test
	public void checkHashStructure() throws Exception {
		DatamapLineValue<Boolean> value = new DatamapLineValue<Boolean>(true);
		assertEquals(true, value.getValue());
		DatamapLineValue<String> str = new DatamapLineValue<String>("Tosser");
		assertEquals("Tosser", str.getValue());
	}

	@Test
	public void exceptionRaisedWhenParserCannotEvenGetSheet() throws Exception {
		ReturnParser parser = new ReturnParser(this.myReturn);
		parser.parse(testFile);
		thrown.expect(CellValueNotFoundException.class);
		thrown.expectMessage("Cannot find sheet Test Sheet 10");
		assertEquals("Test Value 1", parser.getCellValueFromSheet("Test Sheet 10", "B1"));
	}

	@Test
	public void exceptionRaisedWhenParserCannotGetValue() throws Exception {
		ReturnParser parser = new ReturnParser(this.myReturn);
		parser.parse(testFile);
		thrown.expect(CellValueNotFoundException.class);
		thrown.expectMessage("Cannot find a value on sheet Test Sheet 2 in cell B1");
		assertEquals("Test Value 1", parser.getCellValueFromSheet("Test Sheet 2", "B1"));
	}

	@Test
	public void exceptionRaisedWhenValueInCellIsIncorrectTypeAccordingToDatamapLineText() throws Exception {
		gateway = new InMemoryDatamapGateway();
		gateway.createDatamap("Test Datamap 4");
		gateway.addLineToDatamap("Test Datamap 4", "Double Entered", "Test Sheet 1", "C10", TEXT);
		gateway.addLineToDatamap("Test Datamap 4", "Test Date", "Test Sheet 2", "D10", DATE);
		DatamapLine dml = gateway.getDatamapLineFrom("Test Datamap 4", "Double Entered");
		DatamapLine dmlDate = gateway.getDatamapLineFrom("Test Datamap 4", "Test Date");
		InMemoryReturn newReturn = new InMemoryReturn("Test New Return");
		ReturnParser parser = new ReturnParser(newReturn);
		parser.parse(testFile);
		thrown.expect(IncorrectCellTypeException.class);
		thrown.expectMessage("Value at cell C10 on sheet Test Sheet 1 is not a TEXT type");
		assertEquals(12.1, parser.getCellValueFromSheetWithTypeChecking("Test Sheet 1", dml));
	}

	@Test
	public void exceptionRaisedWhenValueInCellIsIncorrectTypeAccordingToDatamapLineDate() throws Exception {
		gateway = new InMemoryDatamapGateway();
		gateway.createDatamap("Test Datamap 4");
		gateway.addLineToDatamap("Test Datamap 4", "Random Key 2", "Test Sheet 2", "D9", DATE);
		DatamapLine dmlDate = gateway.getDatamapLineFrom("Test Datamap 4", "Random Key 2");
		InMemoryReturn newReturn = new InMemoryReturn("Test New Return");
		ReturnParser parser = new ReturnParser(newReturn);
		parser.parse(testFile);
		thrown.expect(IncorrectCellTypeException.class);
		thrown.expectMessage("Value at cell D9 on sheet Test Sheet 2 is not a DATE type");
		assertEquals(12.1, parser.getCellValueFromSheetWithTypeChecking("Test Sheet 2", dmlDate));
	}

	@Test
	public void parseToReturnUsingDatamap() throws Exception {
		// THIS TEST DOES NOT HAVE ASSERTS!
		gateway = new InMemoryDatamapGateway();
		gateway.createDatamap("Test Datamap 5");
		gateway.addLineToDatamap("Test Datamap 5", "Test Key 1", "Test Sheet 1", "B1", TEXT);
		gateway.addLineToDatamap("Test Datamap 5", "Test Key 2", "Test Sheet 1", "B2", TEXT);
		gateway.addLineToDatamap("Test Datamap 5", "Test Key 3", "Test Sheet 1", "B3", TEXT);
		gateway.addLineToDatamap("Test Datamap 5", "The Double 12.0", "Test Sheet 1", "B10", NUMERIC);
		gateway.addLineToDatamap("Test Datamap 5", "The Double 12.1", "Test Sheet 1", "C10", NUMERIC);
		gateway.addLineToDatamap("Test Datamap 5", "Big Complicated Double", "Test Sheet 1", "C11", NUMERIC);
		gateway.addLineToDatamap("Test Datamap 5", "Another Double", "Test Sheet 1", "C12", NUMERIC);
		gateway.addLineToDatamap("Test Datamap 5", "Formula Result", "Test Sheet 1", "C13", NUMERIC);

		gateway.addLineToDatamap("Test Datamap 5", "Random Value", "Test Sheet 2", "D6", TEXT);
		gateway.addLineToDatamap("Test Datamap 5", "Random Key 2", "Test Sheet 2", "D9", NUMERIC);
		gateway.addLineToDatamap("Test Datamap 5", "Date", "Test Sheet 2", "D10", DATE);
		gateway.addLineToDatamap("Test Datamap 5", "Undeclared Date", "Test Sheet 2", "D11", DATE);

		InMemoryReturn newReturn = new InMemoryReturn("Test New Return");
		ReturnParser parser = new ReturnParser(newReturn);
		parser.parse(testFile);
		Datamap datamap = gateway.getDatamap("Test Datamap 5");
		parser.reportReturnValuesToSTDOUT(datamap);
		gateway.deleteAllDatamaps();
	}
	
	@Test
	public void returnInstantiation() throws Exception {
		InMemoryReturn excelReturn = new InMemoryReturn();
		HashMap<?, ?> data = excelReturn.getData();
		assertNotNull(data);
	}
	
	@Test
	public void testDatamapLineDateType() throws Exception {
		gateway = new InMemoryDatamapGateway();
		gateway.createDatamap("Test Datamap");
		gateway.addLineToDatamap("Test Datamap", "Date 1", "Test Sheet 1", "E6", DATE);
		gateway.addLineToDatamap("Test Datamap", "Date 2", "Test Sheet 1", "E7", DATE);
		gateway.addLineToDatamap("Test Datamap", "Date 3", "Test Sheet 1", "E8", DATE);

		InMemoryReturn newReturn = new InMemoryReturn("Test New Return");
		ReturnParser parser = new ReturnParser(newReturn);
		parser.parse(testFile);
		Datamap datamap = gateway.getDatamap("Test Datamap");
		// TODO- reportReturnValuesToSDOUT is presenter/view code, and it is here that formats should be dealt with
		// SimpleDateFormat format = new SimpleDateFormat("d MMMM y");, etc
		parser.reportReturnValuesToSTDOUT(datamap);
	}
	
}
