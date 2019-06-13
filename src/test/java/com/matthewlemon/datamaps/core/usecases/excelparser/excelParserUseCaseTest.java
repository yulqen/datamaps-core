/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matthewlemon.datamaps.core.usecases.excelparser;

import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.entities.InMemoryReturn;
import com.matthewlemon.datamaps.core.exceptions.DatamapNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;
import com.matthewlemon.datamaps.core.gateways.InMemoryDatamapGateway;
import static com.matthewlemon.datamaps.core.parser.DatamapLineType.TEXT;
import com.matthewlemon.datamaps.core.parser.ReturnParser;
import java.io.File;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lemon
 */
public class excelParserUseCaseTest {

	private File testFile;
	private ExtractDataFromExcelFilesUseCase useCase;
	private InMemoryDatamapGateway gateway;
	private Datamap testDatamap;
	private String returnName;
	private ReturnParser parser;
	private InMemoryReturn rtn;

	@Before
	public void setUp() throws DuplicateDatamapException, DatamapNotFoundException {
		ClassLoader classLoader = getClass().getClassLoader();
		rtn = new InMemoryReturn();
		testFile = new File(classLoader.getResource("files/test_populated_template.xlsx").getFile());
		gateway = new InMemoryDatamapGateway();
		gateway.createDatamap("Test Datamap");
		parser = new ReturnParser(rtn);
	}
	
	@Test
	public void testCanExtractDataFromSpreadsheetUsingDatamap() throws EncryptedDocumentException, IOException, DatamapNotFoundException {
		returnName = "Test Return";
		useCase = new ExtractDataFromExcelFilesUseCase();
		gateway.addLineToDatamap("Test Datamap", "Test Key 1", "Test Sheet 1", "B1", TEXT);
		gateway.addLineToDatamap("Test Datamap", "Test Key 2", "Test Sheet 1", "B2", TEXT);
		testDatamap = gateway.getDatamap("Test Datamap");
		rtn = useCase.extractDataFromFileUsingDatamap(returnName, testFile, testDatamap);
		assertEquals("tosser", rtn.getData());	
	}
}
