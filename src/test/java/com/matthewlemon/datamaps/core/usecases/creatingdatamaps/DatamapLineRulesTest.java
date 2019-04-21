package com.matthewlemon.datamaps.core.usecases.creatingdatamaps;

import static com.matthewlemon.datamaps.core.parser.DatamapLineType.NUMERIC;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.junit.Before;
import org.junit.Test;

import com.matthewlemon.datamaps.core.entities.InMemoryReturn;
import com.matthewlemon.datamaps.core.exceptions.DatamapNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;
import com.matthewlemon.datamaps.core.gateways.InMemoryDatamapGateway;
import com.matthewlemon.datamaps.core.parser.ReturnParser;

public class DatamapLineRulesTest {

	private File testFile;
	private InMemoryDatamapGateway gateway;
	private InMemoryReturn myReturn;

	@Before
	public void setUp() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		testFile = new File(classLoader.getResource("files/test_populated_template.xlsx").getFile());
		myReturn = new InMemoryReturn("Test Return");
	}

	@Test
	public void rulesetContainsMultipleRules()
			throws DuplicateDatamapException, DatamapNotFoundException, EncryptedDocumentException, IOException {
		Ruleset ruleset = new Ruleset();
		Rule rule1 = new Rule("D9", "Test Sheet 2", RuleOperator.EQUALS, "E9", "Test Sheet 2");

		gateway = new InMemoryDatamapGateway();
		gateway.createDatamap("Test Datamap");
		gateway.addLineToDatamap("Test Datamap", "Random Number", "Test Sheet 2", "E9", NUMERIC, ruleset);

		InMemoryReturn newReturn = new InMemoryReturn("Test New Return");
		ReturnParser parser = new ReturnParser(newReturn);
		parser.parse(testFile);
		fail("NOT IMPLEMENTED");
	}

}
