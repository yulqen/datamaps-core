package com.matthewlemon.datamaps.core.parser;

public class DatamapValue<T> {

	private T value;

	public DatamapValue(T value) {
		this.value = value;
	}

	public T getValue() {
		return this.value;
	}

}
