package com.matthewlemon.datamaps.core.entities;

import java.util.HashMap;

import com.matthewlemon.datamaps.core.exceptions.CellValueNotFoundException;
import com.matthewlemon.datamaps.core.parser.DatamapValue;

public class InMemoryReturn {

	private HashMap<String, HashMap<String, DatamapValue<?>>> data;
	@SuppressWarnings("unused")
	private String name;

	public InMemoryReturn() {
		this.data = new HashMap<String, HashMap<String, DatamapValue<?>>>();
	}

	public InMemoryReturn(String returnName) {
		this.name = returnName;
		this.data = new HashMap<String, HashMap<String, DatamapValue<?>>>();
	}

	public HashMap<String, HashMap<String, DatamapValue<?>>> getData() {
		return data;
	}

	public DatamapValue<?> getCellValue(String sheetName, String cellRef) throws CellValueNotFoundException {
		HashMap<String, HashMap<String, DatamapValue<?>>> result = data;
		if (result.get(sheetName) == null) {
			throw new CellValueNotFoundException("Cannot find sheet " + sheetName);
		}
		if (result.get(sheetName).get(cellRef) == null) {
			throw new CellValueNotFoundException("Cannot find a value on sheet " + sheetName + " in cell " + cellRef);
		}
		return result.get(sheetName).get(cellRef);
	}

	public DatamapValue<?> getCellValue(String sheetName, DatamapLine dml) throws CellValueNotFoundException {
		HashMap<String, HashMap<String, DatamapValue<?>>> result = data;
		if (result.get(sheetName) == null) {
			throw new CellValueNotFoundException("Cannot find sheet " + sheetName); 
		}
		if (result.get(sheetName).get(dml.getCellRef()) == null) {
			throw new CellValueNotFoundException("Cannot find a value on sheet " + sheetName + " in cell " + dml.getCellRef()); 
		}
		return data.get(dml.getSheetName()).get(dml.getCellRef());
	}
}
