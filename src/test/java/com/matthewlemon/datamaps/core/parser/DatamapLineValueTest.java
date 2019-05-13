package com.matthewlemon.datamaps.core.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class DatamapLineValueTest {

	@Test
	public void valueObjectText() {
		DatamapLineValue<String> value1 = new DatamapLineValue<>("Test Value 1");
		DatamapLineValue<String> value2 = new DatamapLineValue<>("Test Value 2");
		DatamapLineValue<String> value3 = new DatamapLineValue<>("Test Value 1");
		
		assertEquals("DatamapLineValue : class java.lang.String [value Test Value 1]", value1.toString());
		assertTrue("DatamapLineValue with same value should be equal", value1.equals(value3));
		assertFalse("DatamapLineValue with different values should not be equal", value1.equals(value2));
		assertTrue("equal DatamapLineValue objects should have same hash code", value1.hashCode() == value3.hashCode());
	}

	@Test
	public void valueObjectNumeric() {
		DatamapLineValue<Double> value1 = new DatamapLineValue<>(20.1);
		DatamapLineValue<Double> value2 = new DatamapLineValue<>(25.1);
		DatamapLineValue<Double> value3 = new DatamapLineValue<>(20.1);
		
		assertEquals("DatamapLineValue : class java.lang.Double [value 20.1]", value1.toString());
		assertTrue("DatamapLineValue with same value should be equal", value1.equals(value3));
		assertFalse("DatamapLineValue with different values should not be equal", value1.equals(value2));
		assertTrue("equal DatamapLineValue objects should have same hash code", value1.hashCode() == value3.hashCode());
	}
}
