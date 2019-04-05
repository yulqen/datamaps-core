package com.matthewlemon.datamaps.core.entities;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.matthewlemon.datamaps.core.parser.XLSXSheetData;

public class PopulatedTemplate {
	
	// TODO this needs to be a Map, not a list
	List<HashMap> sheetData = new ArrayList<HashMap>();

	public PopulatedTemplate(File file) throws InvalidFormatException, IOException {
		this.importDataFrom(file);
	}

	private void importDataFrom(File filename) throws InvalidFormatException, IOException {
		Workbook workbook = new XSSFWorkbook(filename);
		Iterator<Sheet> sheets = workbook.sheetIterator();
		for (Sheet sheet : workbook) {
			String sheetName = sheet.getSheetName();
			XLSXSheetData data = new XLSXSheetData(filename, sheetName);
			sheetData.add(data.getDataMapHashStringsAsKeys());
		}
	}

	public List<HashMap> getSheetData() {
		return sheetData;
	}

	public String getValue(String cellRef) {
		return (String) sheetData.get(0).get(cellRef);
	}
}
