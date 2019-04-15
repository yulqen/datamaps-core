package com.matthewlemon.datamaps.core.parser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.matthewlemon.datamaps.core.entities.InMemoryReturn;

public class ReturnParser {
	
	private InMemoryReturn returnObj;
	
	public ReturnParser()  {
		this.returnObj = new InMemoryReturn();
	}
	
	public InMemoryReturn getReturn() {
		return this.returnObj;
	}

	public void write(File testFile) throws EncryptedDocumentException, IOException {
		parseWorkbook(testFile);
	}
	
	private void parseWorkbook(File sourceFile) throws EncryptedDocumentException, IOException {
		Workbook workbook = WorkbookFactory.create(sourceFile);
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		parseWorkbookToMapWithEvaluator(workbook, evaluator);
	}

	private void parseWorkbookToMapWithEvaluator(Workbook workbook, FormulaEvaluator evaluator) {
//		DataFormatter formatter = new DataFormatter();
		for (Sheet sheet : workbook) {
			HashMap<String, DatamapValue<?>> sheetData = new HashMap<>();
			for (Row row : sheet) {
				for (Cell cell : row) {
					switch (cell.getCellType()) {
					case BOOLEAN:
						DatamapValue<?> valBool = new DatamapValue<Boolean>(cell.getBooleanCellValue());
						sheetData.put(cell.getAddress().formatAsString(), valBool);
						break;
					case STRING:
						DatamapValue<?> valStr = new DatamapValue<String>(cell.getStringCellValue());
						sheetData.put(cell.getAddress().formatAsString(), valStr);
						break;
					case NUMERIC:
						// TODO: We are only able to handle/convert to doubles here
						DatamapValue<?> valNumeric = new DatamapValue<Double>(cell.getNumericCellValue());
						// TODO: We need to fix how dates strings are handled here
						if (DateUtil.isCellDateFormatted(cell)) {
							sheetData.put(cell.getAddress().formatAsString(), valNumeric);
						} else {
							sheetData.put(cell.getAddress().formatAsString(), valNumeric);
						}
						break;
					case FORMULA:
						DatamapValue<?> formulaResult = new DatamapValue<Double>(evaluator.evaluate(cell).getNumberValue());
						sheetData.put(cell.getAddress().formatAsString(), formulaResult);
						break;
					case BLANK:
						break;
					default:
						DatamapValue<?> defaultStr = new DatamapValue<String>(cell.getStringCellValue());
						sheetData.put(cell.getAddress().formatAsString(), defaultStr);
					}
				}
			}
		returnObj.getData().put(sheet.getSheetName(), sheetData);
		}
	}
}
