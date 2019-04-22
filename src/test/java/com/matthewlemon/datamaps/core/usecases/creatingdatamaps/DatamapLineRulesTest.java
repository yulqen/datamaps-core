package com.matthewlemon.datamaps.core.usecases.creatingdatamaps;

import static com.matthewlemon.datamaps.core.parser.DatamapLineType.NUMERIC;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.entities.InMemoryReturn;
import com.matthewlemon.datamaps.core.exceptions.DatamapLineNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DatamapNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;
import com.matthewlemon.datamaps.core.gateways.InMemoryDatamapGateway;
import com.matthewlemon.datamaps.core.parser.ReturnParser;

public class DatamapLineRulesTest {

	private File testFile;
	private InMemoryDatamapGateway gateway;
	private InMemoryReturn myReturn;
	private ReturnParser parser;
	private RuleSet ruleset;

	@Before
	public void setUp() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		testFile = new File(classLoader.getResource("files/test_populated_template.xlsx").getFile());
		myReturn = new InMemoryReturn("Test Return");
		gateway = new InMemoryDatamapGateway();
		gateway.createDatamap("Test Datamap");
		parser = new ReturnParser(myReturn);
		ruleset = new RuleSet();
		gateway.addLineToDatamap("Test Datamap", "Compare Val 1", "Test Sheet 2", "D14", NUMERIC, ruleset);
		gateway.addLineToDatamap("Test Datamap", "Compare Val 2", "Test Sheet 2", "D15", NUMERIC, ruleset);
	}
	
	@After
	public void tearDown() throws Exception {
		gateway.deleteAllDatamaps();
	}
	
	@Test
	public void testRule() throws Exception {
		DatamapLine dml = gateway.getDatamapLineFrom("Test Datamap", "Compare Val 1");
		DatamapLine dml2 = gateway.getDatamapLineFrom("Test Datamap", "Compare Val 2");
		DatamapLineRule rule = new DatamapLineRule("D14 and D15 match", dml, RuleOperator.EQUALS, dml2, myReturn);
		ruleset.addRule(rule);

		parser.parse(testFile);
		gateway.addLineToDatamap("Test Datamap", "Random Number", "Test Sheet 2", "E9", NUMERIC, ruleset);

		RuleReport report = new RuleReport();
		RuleChecker ruleChecker = new RuleChecker(report, dml, parser.getReturn());
		ruleChecker.check();
		assertEquals(1, report.getReportSize());
		// TODO: need to catch nullpointer when we can't find this report item
		assertFalse(report.getReport().get("D14 and D15 match"));
	}

	@Test
	public void rulesetContainsMultipleRules()
			throws DuplicateDatamapException, DatamapNotFoundException, EncryptedDocumentException, IOException, DatamapLineNotFoundException {
		RuleSet ruleset = new RuleSet();
		DatamapLine dml = gateway.getDatamapLineFrom("Test Datamap", "Compare Val 1");
		DatamapLine dml2 = gateway.getDatamapLineFrom("Test Datamap", "Compare Val 2");
		DatamapLineRule rule = new DatamapLineRule("D9 and E9 match", dml, RuleOperator.EQUALS, dml2, myReturn);
		ruleset.addRule(rule);

		parser.parse(testFile);
		gateway.addLineToDatamap("Test Datamap", "Random Number", "Test Sheet 2", "E9", NUMERIC, ruleset);
		assertEquals(1, parser.getErrorReport().size());
	}
}
