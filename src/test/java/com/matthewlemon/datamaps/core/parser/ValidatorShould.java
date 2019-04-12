package com.matthewlemon.datamaps.core.parser;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class ValidatorShould {

	private static final String TEST_FILE_XLSX = "/home/lemon/Desktop/test_populated_template.xlsx";
	
	@Before
	public void setUp() {
		File testFile = new File(TEST_FILE_XLSX);
	}
	
	@Test
	public void parseSpreadsheetAndReturnExpectedValues() throws Exception {
	}
}
