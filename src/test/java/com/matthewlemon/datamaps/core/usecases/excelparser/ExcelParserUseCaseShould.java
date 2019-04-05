package com.matthewlemon.datamaps.core.usecases.excelparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.matthewlemon.datamaps.core.entities.PopulatedTemplate;
import com.matthewlemon.datamaps.core.exceptions.EmptyCellException;

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
	}
	
	@Test
	public void nonExistentCellThrowsException() throws Exception {
		thrown.expect(EmptyCellException.class);
		thrown.expectMessage("That cell does not have a value in it!");
		PopulatedTemplate populatedTemplate = useCase.createPopulatedTemplate(file);
		assertNull(populatedTemplate.getValue("Test Sheet 1", "T10"));
	}
	
	@Test
	public void sheetDataIsAMapOfMaps() throws Exception {
		PopulatedTemplate populatedTemplate = useCase.createPopulatedTemplate(file);
		assertEquals(populatedTemplate.getValue("Test Sheet 1", "A1"), "Test Key 1");
		assertEquals(populatedTemplate.getValue("Test Sheet 1", "A2"), "Test Key 2");
		assertEquals(populatedTemplate.getValue("Test Sheet 1", "B1"), "Test Value 1");
		assertEquals(populatedTemplate.getValue("Test Sheet 1", "B2"), "Test Value 2");
	}
}
