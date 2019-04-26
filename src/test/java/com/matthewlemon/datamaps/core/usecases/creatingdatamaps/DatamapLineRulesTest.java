package com.matthewlemon.datamaps.core.usecases.creatingdatamaps;

import static com.matthewlemon.datamaps.core.parser.DatamapLineType.NUMERIC;
import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.entities.InMemoryReturn;
import com.matthewlemon.datamaps.core.exceptions.RuleCheckerReportNotFoundException;
import com.matthewlemon.datamaps.core.gateways.InMemoryDatamapGateway;
import com.matthewlemon.datamaps.core.parser.ReturnParser;

public class DatamapLineRulesTest {

	private File testFile;
	private InMemoryDatamapGateway gateway;
	private InMemoryReturn myReturn;
	private ReturnParser parser;
	private RuleSet ruleset;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

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
	public void ruleCheckerThrowsExceptionWhenReportCantBeFound() throws Exception {
		DatamapLine dml = gateway.getDatamapLineFrom("Test Datamap", "Compare Val 1");
		parser.parse(testFile);
		InMemoryReturn rtn = parser.getReturn();
		RuleChecker ruleChecker = new RuleChecker(rtn);
		ruleChecker.check(dml);
		thrown.expect(RuleCheckerReportNotFoundException.class);
		thrown.expectMessage("Cannot find report for Non-existant report");
		assertTrue(ruleChecker.getReportWithString("Non-existant report"));
	}

	@Test
	public void testRuleMechanics() throws Exception {
		DatamapLine dml = gateway.getDatamapLineFrom("Test Datamap", "Compare Val 1"); // value is in cell D14: 230
		DatamapLine dml2 = gateway.getDatamapLineFrom("Test Datamap", "Compare Val 2"); // value is in cell D14: 230
		dml.addRule("D14 and D15 match", RuleOperator.EQUALS, dml2); // this should generate a "true" result

		parser.parse(testFile);

		// testing the internals, not the use case at this stage
		InMemoryReturn rtn = parser.getReturn();
		RuleChecker ruleChecker = new RuleChecker(rtn);
		ruleChecker.check(dml);
		assertEquals(1, ruleChecker.getReportSize());
		assertTrue(ruleChecker.getReport().get("D14 and D15 match"));
	}
	
}
