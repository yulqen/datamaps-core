package com.matthewlemon.datamaps.core.usecases.excelparser;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Ignore;
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
	public void useGatewayToImportDataIntoBlankTemplate() throws Exception {
		PopulatedTemplate template = new PopulatedTemplate();
		template.importDataFrom(testFile);
		assertTrue(template.hasData());
	}
}
