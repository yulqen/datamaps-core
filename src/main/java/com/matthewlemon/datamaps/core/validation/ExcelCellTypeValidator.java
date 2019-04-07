package com.matthewlemon.datamaps.core.validation;

import com.matthewlemon.datamaps.core.gateways.DatamapType;

public class ExcelCellTypeValidator extends Validator {
	
	private DatamapType type;
	
	@Override
	public boolean checkType(DatamapType comparitor) {
		if (comparitor.equals(this.type) ) {
			return true;
		} else {
			return false;
		}
	}
}
