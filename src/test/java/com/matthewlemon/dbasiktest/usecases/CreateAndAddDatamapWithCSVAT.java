package com.matthewlemon.dbasiktest.usecases;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.matthewlemon.dbasiktest.entities.CSVFile;
import com.matthewlemon.dbasiktest.fixtures.CreateableDatamapFixture;

public class CreateAndAddDatamapWithCSVAT {

	private static File testFile;
	private CSVFile csvFile;

	@Before
	public void setUp() {
		ClassLoader classLoader = getClass().getClassLoader();
		testFile = new File(classLoader.getResource("files/test.csv").getFile());
		csvFile = new CSVFile(testFile);
	}

	@Test
	public void userCanCreateDatamapUsingCSVForDatamapLines() {
		CreateableDatamapFixture fixture = new CreateableDatamapFixture();
		fixture.setUpFixture();
		fixture.userCreatesDatamapWithName("Test Datamap");
		fixture.userAddsDataToDatamapUsingCSV("Test Datamap", csvFile);
	}
}
