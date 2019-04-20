package com.matthewlemon.datamaps.core.parser;

// https://stackoverflow.com/questions/32444031/java-enum-store-type-for-later-cast

public enum DatamapLineType { 

	NUMERIC(Double.class),
	TEXT(String.class);
	
	private final Class<?> typeStr;
	
	private DatamapLineType(Class<?> typeStr) {
		this.typeStr = typeStr;
	}
	
	public Class<?> getType() {
		return this.typeStr;
	}
}