package com.matthewlemon.datamaps.core.entities;

// https://stackoverflow.com/questions/32444031/java-enum-store-type-for-later-cast

public enum DatamapTypes { 

	NUMERIC(Double.class),
	TEXT(String.class);
	
	private final Class<?> typeStr;
	
	private DatamapTypes(Class<?> typeStr) {
		this.typeStr = typeStr;
	}
	
	public Class<?> getType() {
		return this.typeStr;
	}
}