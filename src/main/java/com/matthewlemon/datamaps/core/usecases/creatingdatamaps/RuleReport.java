package com.matthewlemon.datamaps.core.usecases.creatingdatamaps;

import java.util.HashMap;
import java.util.Map;

class RuleReport {
	
	private Map<String, Boolean> report;
	
	public Map<String, Boolean> getReport() {
		return this.report;
	}

	public RuleReport() {
		this.report = new HashMap<String, Boolean>();
	}

	public int getReportSize() {
		return report.size();
	}
	
	public void addLineToReport(String ruleName, Boolean result) {
		report.put(ruleName, result);
	}
}
