package com.matthewlemon.dbasiktest.entities;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class CSVFileShould {

	private CSVFile csvFile;
	private File testFile;

	@Before
	public void setUp() {
		ClassLoader classLoader = getClass().getClassLoader();
		testFile = new File(classLoader.getResource("files/csv_obj.csv").getFile());
	}

	@Test
	public void providesItsSourceFile() {
		csvFile = new CSVFile(testFile);
		assertEquals(csvFile.getFile(), testFile);
	}
	
	@Test
	public void setsFileWhenConstructorGivenPath() {
		csvFile = new CSVFile("/home/lemon/code/java/dbasiktest/target/test-classes/files/csv_obj.csv");
		assertEquals(csvFile.getFile(), testFile);
	}
}