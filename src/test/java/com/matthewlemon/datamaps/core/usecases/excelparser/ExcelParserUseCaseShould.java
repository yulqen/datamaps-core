package com.matthewlemon.datamaps.core.usecases.excelparser;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.matthewlemon.datamaps.core.entities.PopulatedTemplate;
import com.matthewlemon.datamaps.core.exceptions.ExcelParserException;

public class ExcelParserUseCaseShould {

	private File file;
	private ExcelParserUseCase useCase;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() {
		ClassLoader classLoader = getClass().getClassLoader();
		useCase = new ExcelParserUseCase();
		file = new File(classLoader.getResource("files/test_populated_template.xlsx").getFile());
	}
	
	@Test
	public void getDataFromPopulatedTemplate() throws Exception {
		PopulatedTemplate populatedTemplate = useCase.createPopulatedTemplate(file);
		//TODO we don't want to be matching on strings here necessarily
		assertEquals(populatedTemplate.getValue("Test Sheet 1", "A1"), "Test Key 1");
		assertEquals(populatedTemplate.getValue("Test Sheet 1", "B1"), "Test Value 1");
		assertEquals(populatedTemplate.getValue("Test Sheet 1", "A2"), "Test Key 2");
		assertEquals(populatedTemplate.getValue("Test Sheet 1", "B2"), "Test Value 2");
		assertEquals(populatedTemplate.getValue("Test Sheet 2", "D6"), "Random Value");
		// TODO - this needs to be the proper type, not a string
		assertEquals(populatedTemplate.getValue("Test Sheet 2", "D9"), "234");
	}
	
	@Test
	public void nonExistentCellThrowsException() throws Exception {
		thrown.expect(ExcelParserException.class);
		thrown.expectMessage("Sheet name: Test Sheet 1 and cell reference T10 does not exist.");
		PopulatedTemplate populatedTemplate = useCase.createPopulatedTemplate(file);
		assertEquals(populatedTemplate.getValue("Test Sheet 1", "T10"), "Meaningless value");
	}
	
	@Test
	public void sheetDataIsAMapOfMaps() throws Exception {
		PopulatedTemplate populatedTemplate = useCase.createPopulatedTemplate(file);
		assertEquals(populatedTemplate.getValue("Test Sheet 1", "A1"), "Test Key 1");
		assertEquals(populatedTemplate.getValue("Test Sheet 1", "A2"), "Test Key 2");
		assertEquals(populatedTemplate.getValue("Test Sheet 1", "B1"), "Test Value 1");
		assertEquals(populatedTemplate.getValue("Test Sheet 1", "B2"), "Test Value 2");
	}
	
	@Test
	public void nonExistentSheetThrowsException() throws Exception {
		thrown.expect(ExcelParserException.class);
		thrown.expectMessage("Sheet name: Test Sheet 2 and cell reference A1 does not exist.");
		PopulatedTemplate populatedTemplate = useCase.createPopulatedTemplate(file);
		assertEquals(populatedTemplate.getValue("Test Sheet 2", "A1"), "Test Key 1");
	}
}
