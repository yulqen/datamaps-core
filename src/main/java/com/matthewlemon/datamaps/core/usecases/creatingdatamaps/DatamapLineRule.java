package com.matthewlemon.datamaps.core.usecases.creatingdatamaps;

import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.entities.InMemoryReturn;
import com.matthewlemon.datamaps.core.exceptions.CellValueNotFoundException;

public class DatamapLineRule {
	
	private String ruleName;
	private RuleOperator operator;
	private DatamapLine dml;
	private DatamapLine dml2;
	private InMemoryReturn rtn;

	public DatamapLineRule(String ruleName, DatamapLine dml, RuleOperator operator, DatamapLine dml2, InMemoryReturn rtn) {
		this.ruleName = ruleName;
		this.operator = operator;
		this.dml = dml;
		this.dml2 = dml2;
		this.rtn = rtn;
	}

	public RuleOperator getOperator() {
		return operator;
	}

	public String getRuleName() {
		return ruleName;
	}
	
	public boolean triggerRule(DatamapLine dml) throws CellValueNotFoundException {
		if (operator == RuleOperator.EQUALS) {
			if (rtn.getCellValue(dml.getSheetName(), dml.getCellRef()) == rtn.getCellValue(dml2.getSheetName(), dml2.getCellRef())) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}
