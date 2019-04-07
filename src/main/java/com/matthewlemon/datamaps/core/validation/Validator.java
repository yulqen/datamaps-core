package com.matthewlemon.datamaps.core.validation;

import com.matthewlemon.datamaps.core.gateways.DatamapType;

public abstract class Validator {
	
	private DatamapType type;

	public void setType(DatamapType type) {
		this.type = type;
	}

	public DatamapType getType() {
		return this.type;
	}

	public boolean checkType(DatamapType comparitor) {
		if (comparitor.equals(this.type) ) {
			return true;
		} else {
			return false;
		}
	}
}
