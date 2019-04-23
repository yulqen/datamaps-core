package com.matthewlemon.datamaps.core.usecases.creatingdatamaps;

import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.entities.InMemoryReturn;
import com.matthewlemon.datamaps.core.exceptions.CellValueNotFoundException;

public class DatamapLineRule {
	
	private String ruleName;
	private RuleOperator operator;
	private DatamapLine dmlToCompare;

	public DatamapLineRule(String ruleName, RuleOperator operator, DatamapLine dmlToCompare) {
		this.ruleName = ruleName;
		this.operator = operator;
		this.dmlToCompare = dmlToCompare;
	}

	public RuleOperator getOperator() {
		return operator;
	}

	public String getRuleName() {
		return ruleName;
	}
	
	public String getRootCellRef() {
		return this.dmlToCompare.getCellRef();
	}
}
