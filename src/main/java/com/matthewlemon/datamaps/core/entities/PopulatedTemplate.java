package com.matthewlemon.datamaps.core.entities;

import java.util.HashMap;

import com.matthewlemon.datamaps.core.exceptions.ExcelParserException;

public class PopulatedTemplate {
	
	private HashMap<String, HashMap<String, String>> sheetData = new HashMap<String, HashMap<String, String>>();

	public HashMap<String,HashMap<String,String>> getSheetData() {
		return sheetData;
	}

	public String getValue(String sheetName, String cellRef) throws ExcelParserException {
		String value;
		try {
			// TODO - we need to pass in a validator object here which checks
			// the value against the Rules in the Validator: type, other rules, etc
			// before passing it on
			value = sheetData.get(sheetName).get(cellRef).toString();
		} catch (NullPointerException e) {
			throw new ExcelParserException(
					"Sheet name: " + sheetName + 
					" and cell reference " + cellRef + " does not exist.");
		}
		return value; 
	}
	
	public void setSheetData(String sheetName, HashMap<String, String> dataMapHashStringsAsKeys) {
		this.sheetData.put(sheetName, dataMapHashStringsAsKeys);
	}
}
