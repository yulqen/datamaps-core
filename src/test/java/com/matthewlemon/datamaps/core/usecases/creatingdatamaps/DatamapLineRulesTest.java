package com.matthewlemon.datamaps.core.usecases.creatingdatamaps;

import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.entities.InMemoryReturn;
import com.matthewlemon.datamaps.core.exceptions.RuleCheckerReportNotFoundException;
import com.matthewlemon.datamaps.core.gateways.InMemoryDatamapGateway;
import static com.matthewlemon.datamaps.core.parser.DatamapLineType.NUMERIC;
import com.matthewlemon.datamaps.core.parser.ReturnParser;
import java.io.File;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DatamapLineRulesTest {

	private File testFile;
	private InMemoryDatamapGateway gateway;
	private ReturnParser parser;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		testFile = new File(classLoader.getResource("files/test_populated_template.xlsx").getFile());
		InMemoryReturn myReturn = new InMemoryReturn("Test Return");
		gateway = new InMemoryDatamapGateway();
		gateway.createDatamap("Test Datamap");
		parser = new ReturnParser(myReturn);
		RuleSet ruleset = new RuleSet();
		gateway.addLineToDatamap("Test Datamap", "Compare Val 1", "Test Sheet 2", "D14", NUMERIC, ruleset);
		gateway.addLineToDatamap("Test Datamap", "Compare Val 2", "Test Sheet 2", "D15", NUMERIC, ruleset);
		gateway.addLineToDatamap("Test Datamap", "Compare Val 3", "Test Sheet 2", "D9", NUMERIC, ruleset);
		gateway.addLineToDatamap("Test Datamap", "Compare Val 4", "Test Sheet 2", "E9", NUMERIC, ruleset);
		gateway.addLineToDatamap("Test Datamap", "Compare Val 5", "Test Sheet 2", "D17", NUMERIC, ruleset);
		gateway.addLineToDatamap("Test Datamap", "Compare Val 6", "Test Sheet 2", "E17", NUMERIC, ruleset);
		gateway.addLineToDatamap("Test Datamap", "Early Date", "Test Sheet 2", "G3", NUMERIC, ruleset);
		gateway.addLineToDatamap("Test Datamap", "Late Date", "Test Sheet 2", "G4", NUMERIC, ruleset);
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
	public void testRuleForEquals() throws Exception {
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

	@Test
	public void testRuleForGreaterThan() throws Exception {
		DatamapLine dml = gateway.getDatamapLineFrom("Test Datamap", "Compare Val 3"); // value is in cell D9: 234
		DatamapLine dml2 = gateway.getDatamapLineFrom("Test Datamap", "Compare Val 4"); // value is in cell D9: 235
		dml.addRule("E9 greater than D9", RuleOperator.GREATER, dml2);

		parser.parse(testFile);

		//TODO: testing the internals, not the use case at this stage
		InMemoryReturn rtn = parser.getReturn();
		RuleChecker ruleChecker = new RuleChecker(rtn);
		ruleChecker.check(dml);
		assertEquals(1, ruleChecker.getReportSize());
		assertTrue(ruleChecker.getReport().get("E9 greater than D9"));
	}

	@Test
	public void testRuleForLessThan() throws Exception {
		DatamapLine dml = gateway.getDatamapLineFrom("Test Datamap", "Compare Val 5"); // value is in cell D17: 200
		DatamapLine dml2 = gateway.getDatamapLineFrom("Test Datamap", "Compare Val 6"); // value is in cell E17: 201
		dml.addRule("D17 less than E17", RuleOperator.LESS, dml2);

		parser.parse(testFile);

		InMemoryReturn rtn = parser.getReturn();
		RuleChecker ruleChecker = new RuleChecker(rtn);
		ruleChecker.check(dml);
		assertEquals(1, ruleChecker.getReportSize());
		assertTrue(ruleChecker.getReport().get("D17 less than E17"));
	}

	// TODO: also test the other dml - that it works with it's partner. Does that make sense?

	@Test
	public void testEarlyLateDateEarlier() throws Exception {
		DatamapLine dml = gateway.getDatamapLineFrom("Test Datamap", "Early Date"); // value is in cell G3: 21/1/19
		DatamapLine dml2 = gateway.getDatamapLineFrom("Test Datamap", "Late Date"); // value is in cell G4: 21/10/19
		dml.addRule("G3 earlier than G4", RuleOperator.EARLIER, dml2);

		parser.parse(testFile);

		InMemoryReturn rtn = parser.getReturn();
		RuleChecker ruleChecker = new RuleChecker(rtn);
		ruleChecker.check(dml);
		assertEquals(1, ruleChecker.getReportSize());
		assertTrue(ruleChecker.getReport().get("G3 earlier than G4"));
	}

	@Test
	public void testEarlyLateDateLater() throws Exception {
		DatamapLine dml = gateway.getDatamapLineFrom("Test Datamap", "Early Date"); // value is in cell G3: 21/1/19
		DatamapLine dml2 = gateway.getDatamapLineFrom("Test Datamap", "Late Date"); // value is in cell G4: 21/10/19
		// NOTICE difference here! Adding the rule to dml2
		dml2.addRule("G4 later than G3", RuleOperator.LATER, dml);


		parser.parse(testFile);

		InMemoryReturn rtn = parser.getReturn();
		RuleChecker ruleChecker = new RuleChecker(rtn);
		ruleChecker.check(dml2);
		assertEquals(1, ruleChecker.getReportSize());
		assertTrue(ruleChecker.getReport().get("G4 later than G3"));
	}
}
