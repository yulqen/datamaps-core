package com.matthewlemon.datamaps.core.usecases.excelparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.matthewlemon.datamaps.core.entities.PopulatedTemplate;
import com.matthewlemon.datamaps.core.exceptions.EmptyCellException;

public class ExcelParserUseCaseShould {

	private File testFile;
	private ExcelParserUseCase useCase;

	@Before
	public void setUp() {
		ClassLoader classLoader = getClass().getClassLoader();
		useCase = new ExcelParserUseCase();
		testFile = new File(classLoader.getResource("files/test_populated_template.xlsx").getFile());
	}
	
	@Test
	public void getDataFromPopulatedTemplate() throws Exception {
		PopulatedTemplate template = useCase.createPopulatedTemplate(testFile);
		//TODO we don't want to be matching on strings here necessarily
		assertEquals(template.getValue("Test Sheet 1", "A1"), "Test Key 1");
		assertEquals(template.getValue("Test Sheet 1", "B1"), "Test Value 1");
		assertEquals(template.getValue("Test Sheet 1", "A2"), "Test Key 2");
		assertEquals(template.getValue("Test Sheet 1", "B2"), "Test Value 2");
	}
	
	@Test(expected=EmptyCellException.class)
	public void nonExistentCellThrowsException() throws Exception {
		PopulatedTemplate template = useCase.createPopulatedTemplate(testFile);
		assertNull(template.getValue("Test Sheet 1", "T10"));
	}
	
	@Test
	public void sheetDataIsAMapOfMaps() throws Exception {
		PopulatedTemplate template = useCase.createPopulatedTemplate(testFile);
		assertEquals(template.getValue("Test Sheet 1", "A1"), "Test Key 1");
		assertEquals(template.getValue("Test Sheet 1", "A2"), "Test Key 2");
		assertEquals(template.getValue("Test Sheet 1", "B1"), "Test Value 1");
		assertEquals(template.getValue("Test Sheet 1", "B2"), "Test Value 2");
	}
}
