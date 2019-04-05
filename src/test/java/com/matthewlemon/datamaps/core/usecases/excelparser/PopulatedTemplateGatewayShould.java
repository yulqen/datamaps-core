package com.matthewlemon.datamaps.core.usecases.excelparser;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.matthewlemon.datamaps.core.TestSetup;
import com.matthewlemon.datamaps.core.entities.PopulatedTemplate;
import com.matthewlemon.datamaps.core.gateways.ImportFileGateway;
import com.matthewlemon.datamaps.core.gateways.PopulatedTemplateGateway;

public class PopulatedTemplateGatewayShould {
	
	private ExcelParserUseCase useCase;
	private File testFile;
	private ImportFileGateway gateway;

	@Before
	public void setUp() {
		TestSetup.setupContext();
		ClassLoader classLoader = getClass().getClassLoader();
		testFile = new File(classLoader.getResource("files/test_populated_template.xlsx").getFile());
		useCase = new ExcelParserUseCase();
		gateway = new PopulatedTemplateGateway();
	}
	
	@Test
	public void sheetDataFromXLSXFileIsMapofMap() throws Exception {
		PopulatedTemplate template = gateway.createPopulatedTemplate(testFile);
	}

}
