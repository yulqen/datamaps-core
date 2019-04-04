package com.matthewlemon.datamaps.core.usecases.excelparser;

import java.io.File;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.matthewlemon.datamaps.core.entities.CSVFile;
import com.matthewlemon.datamaps.core.fixtures.ExcelParserFixture;
import com.matthewlemon.datamaps.core.gateways.DatamapTextType;

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
	@Ignore("Not having this yet.")
	public void tracerBulletTest() throws Exception {
		fixture.setUpFixture();
		fixture.createablePopulatedTemplate(testFile);
		fixture.userCanSetUpNewReturn("Test Return");
		fixture.userCreatesDatamapWithName("Test Datamap");
        fixture.userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 1", "Sheet 1", "B1", new DatamapTextType());
        fixture.userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 2", "Sheet 1", "B2", new DatamapTextType());
        fixture.userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 3", "Sheet 1", "B3", new DatamapTextType());
	}
}
