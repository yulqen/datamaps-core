package com.matthewlemon.datamaps.core.gateways;

public class DatamapTextType extends DatamapType {

	public DatamapTextType() {
		super();
	}

	@Override
	public Object convert(String value) {
		return value;
	}
}
