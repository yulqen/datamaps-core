package com.matthewlemon.datamaps.core.usecases.excelparser;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.matthewlemon.datamaps.core.TestSetup;
import com.matthewlemon.datamaps.core.entities.PopulatedTemplate;
import com.matthewlemon.datamaps.core.exceptions.ExcelParsedValueException;
import com.matthewlemon.datamaps.core.gateways.DatamapTextType;
import com.matthewlemon.datamaps.core.gateways.DatamapType;
import com.matthewlemon.datamaps.core.gateways.ImportFileGateway;
import com.matthewlemon.datamaps.core.gateways.PopulatedTemplateGateway;
import com.matthewlemon.datamaps.core.validation.ExcelCellTypeValidator;
import com.matthewlemon.datamaps.core.validation.Validator;

public class PopulatedTemplateGatewayShould {
	
	private File testFile;
	private ImportFileGateway gateway;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() {
		TestSetup.setupContext();
		ClassLoader classLoader = getClass().getClassLoader();
		testFile = new File(classLoader.getResource("files/test_populated_template.xlsx").getFile());
		gateway = new PopulatedTemplateGateway();
	}
	
	@Test
	public void sheetDataFromXLSXFileIsMapofMap() throws Exception {
		PopulatedTemplate template = gateway.createPopulatedTemplate(testFile);
		assertEquals(template.getValue("Test Sheet 1", "A1"), "Test Key 1");
	}
	
	@Test
	public void throwExceptionWhenCellValueIsWrongType() throws Exception {
		Validator validator = new ExcelCellTypeValidator();
		DatamapType type = new DatamapIntegerType();
		validator.setType(type);
		thrown.expect(ExcelParsedValueException.class);
		thrown.expectMessage("Expected value to be of DatamapIntegerType.");
		PopulatedTemplate template = gateway.createPopulatedTemplate(testFile);
		assertEquals(template.getValue("Test Sheet 1", "A1", validator), "Test Key 1");
		assertEquals(template.getValue("Test Sheet 1", "B10", validator), "12");
	}

	@Test
	public void correctlyParseWhenCorrectType() throws Exception {
		Validator validator = new ExcelCellTypeValidator();
		DatamapType type = new DatamapIntegerType();
		validator.setType(type);
		PopulatedTemplate template = gateway.createPopulatedTemplate(testFile);
		assertEquals(template.getValue("Test Sheet 1", "A10", validator), 10);
		assertEquals(template.getValue("Test Sheet 1", "B10", validator), 12);
	}
}
