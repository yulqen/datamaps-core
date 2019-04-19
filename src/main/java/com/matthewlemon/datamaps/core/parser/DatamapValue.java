package com.matthewlemon.datamaps.core.parser;

public class DatamapValue<T> {

	private T value;
	private Class<?> klazz;

	public DatamapValue(T value) {
		this.value = value;
		this.klazz = value.getClass();
	}

	public T getValue() {
		return this.value;
	}
	
	public Class<?> getType() {
		return this.klazz;
	}

}
