package com.matthewlemon.dbasiktest.entities;

public class DatamapLine {

    private String key;
    private String sheetName;
    private String cellRef;

    public DatamapLine(String key, String sheetName, String cellRef) {
        this.key = key;
        this.sheetName = sheetName;
        this.cellRef = cellRef;
    }

    public String getKey() {
        return this.key;
    }
}
