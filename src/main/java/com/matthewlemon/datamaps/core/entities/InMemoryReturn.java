package com.matthewlemon.datamaps.core.entities;

import com.matthewlemon.datamaps.core.exceptions.CellValueNotFoundException;
import com.matthewlemon.datamaps.core.parser.DatamapLineValue;
import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryReturn {

	private HashMap<String, HashMap<String, DatamapLineValue<?>>> data;
	@SuppressWarnings("unused")
	private String name;

	public InMemoryReturn() {
		this.data = new HashMap<String, HashMap<String, DatamapLineValue<?>>>();
	}

	public InMemoryReturn(String returnName) {
		this.name = returnName;
		this.data = new HashMap<String, HashMap<String, DatamapLineValue<?>>>();
	}

	public DatamapLineValue<?> getCellValue(String sheetName, DatamapLine dml) throws CellValueNotFoundException {
		HashMap<String, HashMap<String, DatamapLineValue<?>>> result = data;
		if (result.get(sheetName) == null) {
			throw new CellValueNotFoundException("Cannot find sheet " + sheetName); 
		}
		if (result.get(sheetName).get(dml.getCellRef()) == null) {
			throw new CellValueNotFoundException("Cannot find a value on sheet " + sheetName + " in cell " + dml.getCellRef()); 
		}
		return data.get(dml.getSheetName()).get(dml.getCellRef());
	}

	public DatamapLineValue<?> getCellValue(String sheetName, String cellRef) throws CellValueNotFoundException {
		HashMap<String, HashMap<String, DatamapLineValue<?>>> result = data;
		if (result.get(sheetName) == null) {
			throw new CellValueNotFoundException("Cannot find sheet " + sheetName);
		}
		if (result.get(sheetName).get(cellRef) == null) {
			throw new CellValueNotFoundException("Cannot find a value on sheet " + sheetName + " in cell " + cellRef);
		}
		return result.get(sheetName).get(cellRef);
	}

	public HashMap<String, HashMap<String, DatamapLineValue<?>>> getData() {
		return data;
	}

	public int getActiveSheets() {
		if (data.size() > 0) {
			return data.size();
		}
		else {
			return 0;
		}
	}

	public ArrayList getActiveSheetNames() {
		if (this.data.size() > 0) {
			ArrayList sheetNames = new ArrayList();
			for (HashMap<String, DatamapLineValue<?>> value : data.values()) {
				sheetNames.add(value);
			}
			return sheetNames;
		}
		else return null;
	}
}
