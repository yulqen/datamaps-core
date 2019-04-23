package com.matthewlemon.datamaps.core.entities;

import com.matthewlemon.datamaps.core.parser.DatamapLineType;
import com.matthewlemon.datamaps.core.usecases.creatingdatamaps.DatamapLineRule;
import com.matthewlemon.datamaps.core.usecases.creatingdatamaps.RuleOperator;
import com.matthewlemon.datamaps.core.usecases.creatingdatamaps.RuleSet;

public class DatamapLine {

    private String key;
    private String sheetName;
    private String cellRef;
	private DatamapLineType type;
	private RuleSet ruleSet;


    public DatamapLine(String key, String sheetName, String cellRef) {
        this.key = key;
        this.sheetName = sheetName;
        this.cellRef = cellRef;
	}

	public DatamapLine(String key, String sheetName, String cellRef, DatamapLineType type) {
        this.key = key;
        this.sheetName = sheetName;
        this.cellRef = cellRef;
        this.type = type;
	}

	public DatamapLine(String key, String sheetName, String cellRef, DatamapLineType type, RuleSet ruleSet) {
        this.key = key;
        this.sheetName = sheetName;
        this.cellRef = cellRef;
        this.type = type;
        this.ruleSet = ruleSet;
	}
	
	public DatamapLineType getDatamapType() {
		return this.type;
	}

	public String getSheetName() {
		return sheetName;
	}

	public String getCellRef() {
		return cellRef;
	}

    public String getKey() {
        return this.key;
    }

	public RuleSet getRuleSet() {
		return this.ruleSet;
	}

	public void addRule(String ruleName, RuleOperator operator, DatamapLine dmlToCompareAgainst) {
		DatamapLineRule rule = new DatamapLineRule(ruleName, operator, dmlToCompareAgainst);
		this.ruleSet.addRule(rule);
	}
}
