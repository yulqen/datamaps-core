/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matthewlemon.datamaps.core.usecases.excelparser;

import com.matthewlemon.datamaps.core.TestSetup;
import com.matthewlemon.datamaps.core.entities.Datamap;
import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author lemon
 */
public class excelParserUseCaseTest {

	private File testFile;
	private ExtractDataFromExcelFilesUseCase useCase;
	private Datamap testDatamap;

	@Before
	public void setUp() {
		TestSetup.setupContext();
	}
	
	public excelParserUseCaseTest() {
		ClassLoader classLoader = getClass().getClassLoader();
		testFile = new File(classLoader.getResource("files/test_populated_template.xlsx").getFile());
//	ReturnCollection collection = useCase.createReturnCollection("Series 1 Collection");
//		useCase.parseFiles(testFile, collection);
//		assertEquals(useCase.getFirstParsedFile(collection), "Test ");
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	
	@After
	public void tearDown() {
	}

	@Test
	public void testCanExtractDataFromSpreadsheetUsingDatamap() {
			
	}
}
