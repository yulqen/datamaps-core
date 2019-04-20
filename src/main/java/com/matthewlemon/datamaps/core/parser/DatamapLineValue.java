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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatamapLineValue<?> other = (DatamapLineValue<?>) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	public Class<?> getType() {
		return this.klazz;
	}
	
	@Override
	public String toString() {
		return "DatamapLineValue : " + klazz + " [value " + value + "]";
	}

}
