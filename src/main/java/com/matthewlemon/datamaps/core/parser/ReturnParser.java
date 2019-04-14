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
	
	public void write(File testFile) throws EncryptedDocumentException, IOException {
		parseWorkbook(testFile);
	}
	
	public InMemoryReturn getReturn() {
		return this.returnObj;
	}

	private void parseWorkbook(File sourceFile) throws EncryptedDocumentException, IOException {
		Workbook workbook = WorkbookFactory.create(sourceFile);
		FormulaEvaluator evaluator = generateEvaluator(workbook);
		parseWorkbookToMapWithEvaluator(workbook, evaluator);
	}

	private FormulaEvaluator generateEvaluator(Workbook workbook) {
		try {
			FormulaEvaluator evaluator = createXSSFEvaluator(workbook);
			return evaluator;
		} catch (ClassCastException e) {
			FormulaEvaluator evaluator = createHSSFEvaluator(workbook);
			return evaluator;
		}
	}

	private FormulaEvaluator createHSSFEvaluator(Workbook workbook) {
		FormulaEvaluator evaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);
		return evaluator;
	}

	private FormulaEvaluator createXSSFEvaluator(Workbook workbook) {
		FormulaEvaluator evaluator = new XSSFFormulaEvaluator((XSSFWorkbook) workbook);
		return evaluator;
	}

	private void parseWorkbookToMapWithEvaluator(Workbook workbook, FormulaEvaluator evaluator) {
		DataFormatter formatter = new DataFormatter();
		for (Sheet sheet : workbook) {
			HashMap<String, DatamapValue<?>> sheetHash = new HashMap<>();
			for (Row row : sheet) {
				for (Cell cell : row) {
					switch (cell.getCellType()) {
					case BOOLEAN:
						// here we create a DatamapTypeBoolean and add it to the Map
						// e.g:
						// this.data.put(cell.getAddressToString(), DataTypeBoolean(cell.getBooleanCellValue())
						System.out.println("Getting " + sheet.getSheetName() + " " +  cell.getAddress().formatAsString() + " Boolean:\t" + cell.getBooleanCellValue());
						DatamapValue<?> valBool = new DatamapValue<Boolean>(cell.getBooleanCellValue());
						sheetHash.put(cell.getAddress().formatAsString(), valBool);
//						System.out.print(cell.getBooleanCellValue());
						break;
					case STRING:
						System.out.println("Getting " + sheet.getSheetName() + " " + cell.getAddress().formatAsString() + " String:\t" + cell.getStringCellValue());
						DatamapValue<?> valStr = new DatamapValue<String>(cell.getStringCellValue());
						sheetHash.put(cell.getAddress().formatAsString(), valStr);
//						System.out.print(cell.getRichStringCellValue().getString());
						break;
					case NUMERIC:
						System.out.println("Getting " + sheet.getSheetName() + " " + cell.getAddress().formatAsString() + " Numeric:\t" + cell.getNumericCellValue());
						// TODO: We are only able to handle/convert to doubles here
						DatamapValue<?> valNumeric = new DatamapValue<Double>(cell.getNumericCellValue());
						// TODO: We need to fix how dates strings are handled here
						if (DateUtil.isCellDateFormatted(cell)) {
//							System.out.print(cell.getDateCellValue());
							sheetHash.put(cell.getAddress().formatAsString(), valNumeric);
						} else {
							sheetHash.put(cell.getAddress().formatAsString(), valNumeric);
						}
						break;
					case FORMULA:
						System.out.println("Getting " + sheet.getSheetName() + " " + cell.getAddress().formatAsString() + " Formula result:\t" + formatter.formatCellValue(cell, evaluator));
						DatamapValue<?> formulaResult = new DatamapValue<String>(formatter.formatCellValue(cell, evaluator));
						sheetHash.put(cell.getAddress().formatAsString(), formulaResult);
//						System.out.print(formatter.formatCellValue(cell, evaluator));
						break;
					case BLANK:
						break;
					default:
						System.out.println("");
						DatamapValue<?> defaultStr = new DatamapValue<String>(cell.getStringCellValue());
						sheetHash.put(cell.getAddress().formatAsString(), defaultStr);
					}
				}
			}
		returnObj.getData().put(sheet.getSheetName(), sheetHash);
		}
	}
}
