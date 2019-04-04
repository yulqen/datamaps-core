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

	List<HashMap> sheetData = new ArrayList<HashMap>();

	public boolean hasData() {
		if (sheetData.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void importDataFrom(File filename) throws InvalidFormatException, IOException {
		Workbook workbook = new XSSFWorkbook(filename);
		Iterator<Sheet> sheets = workbook.sheetIterator();
		for (Sheet sheet : workbook) {
			String sheetName = sheet.getSheetName();
			XLSXSheetData data = new XLSXSheetData(filename, sheetName);
			sheetData.add(data.getDataMapHash());
		}
	}
}
