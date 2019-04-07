package com.matthewlemon.datamaps.core.usecases.excelparser;

import com.matthewlemon.datamaps.core.exceptions.ExcelParsedValueException;
import com.matthewlemon.datamaps.core.gateways.DatamapType;

public class DatamapIntegerType extends DatamapType {
	
	public DatamapIntegerType() {
		super();
	}

	@Override
	public Object convert(String value) throws ExcelParsedValueException {
		try { return Integer.parseInt(value); }
		catch (NumberFormatException e) {
			throw new ExcelParsedValueException("Expected value to be of DatamapIntegerType.");
		}
	}
}
