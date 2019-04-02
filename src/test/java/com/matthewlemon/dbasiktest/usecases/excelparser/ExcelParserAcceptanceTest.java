package com.matthewlemon.dbasiktest.usecases.excelparser;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.matthewlemon.dbasiktest.entities.CSVFile;
import com.matthewlemon.dbasiktest.fixtures.ExcelParserFixture;
import com.matthewlemon.dbasiktest.gateways.DatamapTextType;

public class ExcelParserAcceptanceTest {
	
	private ExcelParserFixture fixture = new ExcelParserFixture();
	private File testFile;
	private CSVFile csvFile;
	
	@Before
	public void setUp() {
		ClassLoader classLoader = getClass().getClassLoader();
		testFile = new File(classLoader.getResource("files/test_populated_template.xlsx").getFile());
		csvFile = new CSVFile(testFile);
	}

	@Test
	public void tracerBulletTest() throws Exception {
		fixture.setUpFixture();
		assertTrue(fixture.userCanSetUpPopulatedTemplateWithName(testFile));
		assertTrue(fixture.userCanSetUpNewReturn("Test Return"));
		fixture.userCreatesDatamapWithName("Test Datamap");
        fixture.userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 1", "Sheet 1", "B1", new DatamapTextType());
        fixture.userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 2", "Sheet 1", "B2", new DatamapTextType());
        fixture.userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 3", "Sheet 1", "B3", new DatamapTextType());
	}
}
