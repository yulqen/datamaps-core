package com.matthewlemon.datamaps.core.parser;

public class DatamapLineValue<T> {

	private T value;
	private Class<?> klazz;

	public DatamapLineValue(T value) {
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
