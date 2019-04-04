package com.matthewlemon.datamaps.core.usecases.excelparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.matthewlemon.datamaps.core.entities.PopulatedTemplate;

public class ExcelParserUseCaseShould {

	private File testFile;
	private ExcelParserUseCase useCase;

	@Before
	public void setUp() {
		ClassLoader classLoader = getClass().getClassLoader();
		testFile = new File(classLoader.getResource("files/test_populated_template.xlsx").getFile());
	}
	
	@Test
	public void createEmptyPopulatedTemplateObject() throws Exception {
		PopulatedTemplate template = new PopulatedTemplate();
		assertFalse(template.hasData());
	}
	
	@Test
	public void useGatewayToImportDataIntoBlankTemplateWithConstructor() throws Exception {
		PopulatedTemplate template = new PopulatedTemplate(testFile);
		assertTrue(template.hasData());
	}
	
	@Test
	public void getDataFromPopulatedTemplate() throws Exception {
		useCase = new ExcelParserUseCase();
		PopulatedTemplate template = useCase.createPopulatedTemplate(testFile);
		assertEquals(template.getValue("A1"), "Test Key 1");
		assertEquals(template.getValue("B1"), "Test Value 1");
	}
}
