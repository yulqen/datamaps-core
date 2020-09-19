package com.matthewlemon.datamaps.core.usecases.creatingdatamaps;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.matthewlemon.datamaps.core.entities.CSVFile;
import com.matthewlemon.datamaps.core.exceptions.DatamapNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;
import com.matthewlemon.datamaps.core.fixtures.CreateableDatamapFixture;

public class CreateAndAddDatamapWithCSVAT {

	private CSVFile csvFile;

	@Before
	public void setUp() {
		ClassLoader classLoader = getClass().getClassLoader();
		File testFile = new File(classLoader.getResource("files/test.csv").getFile());
		csvFile = new CSVFile(testFile);
	}

	@Test
	public void userCanCreateDatamapUsingCSVForDatamapLines() throws DuplicateDatamapException, DatamapNotFoundException {
		CreateableDatamapFixture fixture = new CreateableDatamapFixture();
		fixture.setUpFixture();
		fixture.userCreatesDatamapWithName("Test Datamap");
		fixture.userAddsDataToDatamapUsingCSV("Test Datamap", csvFile);
	}
}
