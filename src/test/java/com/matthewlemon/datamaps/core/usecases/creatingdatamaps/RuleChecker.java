package com.matthewlemon.datamaps.core.usecases.creatingdatamaps;

import java.util.Set;

import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.entities.InMemoryReturn;
import com.matthewlemon.datamaps.core.exceptions.CellValueNotFoundException;
import com.matthewlemon.datamaps.core.parser.DatamapLineValue;

public class RuleChecker {
	
	private RuleReport report;
	private DatamapLine dml;
	private InMemoryReturn rtn;
	
	public RuleChecker(RuleReport report, DatamapLine dml, InMemoryReturn returnObj) throws CellValueNotFoundException {
		this.report = report;
		this.dml = dml;
		this.rtn = returnObj;
	}
	
	public void check() throws CellValueNotFoundException {

		Set<DatamapLineRule> ruleSet = dml.getRuleSet().getRules();

		for (DatamapLineRule rule : ruleSet) {
			// TODO: here we need to do a switch and trigger rule based on values from the return
			switch (rule.getOperator()) {
				case EQUALS:
					DatamapLineValue<?> value1 = rtn.getCellValue(dml.getSheetName(), dml);
					DatamapLineValue<?> value2 = rtn.getCellValue(dml.getSheetName(), dml);
					this.report.addLineToReport(rule.getRuleName(), rule.triggerRule(dml));
					break;
			}
		}
	}
}
