package com.matthewlemon.datamaps.core.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.matthewlemon.datamaps.core.entities.InMemoryReturn;

public class ExcelParserTest {
	
	private File testFile;
	
	@Before
	public void setUp() throws Exception {
    	ClassLoader classLoader = getClass().getClassLoader();
        testFile = new File(classLoader.getResource("files/test_populated_template.xlsx").getFile());
	}
	
	@Test
	public void checkHashStructure() throws Exception {
		DatamapValue value = new DatamapValue<Boolean>(true);
		assertEquals(value.getValue(), true);
		DatamapValue str = new DatamapValue<String>("Tosser");
		assertEquals(str.getValue(), "Tosser");
	}
	
	@Test
	public void returnInstantiation() throws Exception {
		InMemoryReturn excelReturn = new InMemoryReturn();
		HashMap data = excelReturn.getData();
		assertNotNull(data);
	}
	
	@Test
	public void returnParserExists() throws EncryptedDocumentException, IOException {
		ReturnParser parser = new ReturnParser();
		parser.write(testFile);
		assertEquals(parser.getReturn().getCellValue("Test Sheet 1", "B1").getValue(), "Test Value 1");
		assertEquals(parser.getReturn().getCellValue("Test Sheet 1", "C10").getValue(), 12.1);
	}
}
