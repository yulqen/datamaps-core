package com.matthewlemon.datamaps.core.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.matthewlemon.datamaps.core.exceptions.EmptyCellException;

public class PopulatedTemplate {
	
	// TODO this needs to be a Map, not a list
	public List<HashMap> sheetData = new ArrayList<HashMap>();

	public List<HashMap> getSheetData() {
		return sheetData;
	}

	public String getValue(String cellRef) throws EmptyCellException {
		String value;
		try {
			value = sheetData.get(0).get(cellRef).toString();
		} catch (NullPointerException e) {
			throw new EmptyCellException("That cell does have a value in it!");
		}
		return value; 
	}
}
