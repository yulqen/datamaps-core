package com.matthewlemon.datamaps.core.gateways;

import com.matthewlemon.datamaps.core.exceptions.ExcelParsedValueException;

public abstract class DatamapType {

	public abstract Object convert(String value) throws ExcelParsedValueException;
}
