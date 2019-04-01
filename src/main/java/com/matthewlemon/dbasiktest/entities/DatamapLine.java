package com.matthewlemon.dbasiktest.entities;

import com.matthewlemon.dbasiktest.gateways.DatamapType;

public class DatamapLine {

    private String key;
    private String sheetName;
    private String cellRef;
    private DatamapType type;

	public DatamapType getType() {
		return type;
	}

	public DatamapLine(String key, String sheetName, String cellRef, DatamapType type) {
        this.key = key;
        this.sheetName = sheetName;
        this.cellRef = cellRef;
        this.type = type;
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
