package com.matthewlemon.datamaps.core.entities;

import java.util.HashMap;

import com.matthewlemon.datamaps.core.parser.DatamapValue;

public class InMemoryReturn {

	private HashMap<String, HashMap<String, DatamapValue<?>>> data;
	
	public InMemoryReturn() {
		this.data = new HashMap<String, HashMap<String,DatamapValue<?>>>();
	}

	public HashMap<String, HashMap<String, DatamapValue<?>>> getData() {
		return data;
	}

	public DatamapValue<?> getCellValue(String sheetName, String cellRef) {
		return data.get(sheetName).get(cellRef);
	}

}
