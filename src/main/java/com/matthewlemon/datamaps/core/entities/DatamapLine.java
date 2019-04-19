package com.matthewlemon.datamaps.core.entities;

import com.matthewlemon.datamaps.core.parser.DatamapType;

public class DatamapLine {

    private String key;
    private String sheetName;
    private String cellRef;
    private DatamapType type;
	private DatamapTypes typeEnum;

	public DatamapType getType() {
		return type;
	}

	public DatamapLine(String key, String sheetName, String cellRef, DatamapType type) {
        this.key = key;
        this.sheetName = sheetName;
        this.cellRef = cellRef;
        this.type = type;
    }

    public DatamapLine(String key, String sheetName, String cellRef) {
        this.key = key;
        this.sheetName = sheetName;
        this.cellRef = cellRef;
	}

	public DatamapLine(String key, String sheetName, String cellRef, DatamapTypes type) {
        this.key = key;
        this.sheetName = sheetName;
        this.cellRef = cellRef;
        this.typeEnum = type;
	}
	
	public DatamapTypes getDatamapTypes() {
		return this.typeEnum;
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
}
