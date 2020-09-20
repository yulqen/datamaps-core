package com.matthewlemon.datamaps.core.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.entities.InMemoryReturn;
import com.matthewlemon.datamaps.core.exceptions.CellValueNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.IncorrectCellTypeException;

public class ReturnParser {

	private InMemoryReturn returnObj;
	@SuppressWarnings("unused")
	private Datamap datamap;
	private ArrayList<String> errorList;

	public ReturnParser() {
		this.returnObj = new InMemoryReturn();
	}

	public ReturnParser(InMemoryReturn myReturn) {
		this.returnObj = myReturn;
	}

	public Object getCellValueFromSheet(String sheetName, DatamapLine datamapLine) throws CellValueNotFoundException {
		return this.returnObj.getCellValue(sheetName, datamapLine.getCellRef()).getValue();
	}

	public Object getCellValueFromSheet(String sheetName, String cellRef) throws CellValueNotFoundException {
		return this.returnObj.getCellValue(sheetName, cellRef).getValue();
	}

	public ArrayList<String> getErrorReport() {
		return this.errorList;
	}

	// TODO: this might have to be refactored so that it can be run on a data HashMap, depending if it is called for
	// 	by the use case. Use case's responsible to sort this out, depending on what the user asks for.
	public Object getCellValueFromSheetWithTypeChecking(String sheetName, DatamapLine datamapLine)
			throws CellValueNotFoundException, IncorrectCellTypeException {
		DatamapLineType enumType = datamapLine.getDatamapType();

		Class<?> classDeclaredInDatamapLine = datamapLine.getDatamapType().getType();
		Class<?> classOfCellValue = this.returnObj.getCellValue(sheetName, datamapLine).getType();
		if (!classDeclaredInDatamapLine.equals(classOfCellValue)) {
			throw new IncorrectCellTypeException("Value at cell " + datamapLine.getCellRef() + " on sheet " + sheetName
					+ " is not a " + enumType.name() + " type");
		}
		// TODO: implement checking of rule here
		return this.returnObj.getCellValue(sheetName, datamapLine).getValue();
	}

	public InMemoryReturn getReturn() {
		return this.returnObj;
	}

	public void parse(File testFile) throws EncryptedDocumentException, IOException {
		parseWorkbook(testFile);
	}

	public void reportReturnValuesToSTDOUT(Datamap datamap)
			throws CellValueNotFoundException, IncorrectCellTypeException {
		for (DatamapLine dml : datamap.getDatamapLines()) {
			System.out.println(dml.getSheetName() + ": " + dml.getKey() + ": "
					+ this.getCellValueFromSheetWithTypeChecking(dml.getSheetName(), dml));
		}
	}

	private void parseWorkbook(File sourceFile) throws EncryptedDocumentException, IOException {
		Workbook workbook = WorkbookFactory.create(sourceFile);
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		parseWorkbookToMapWithEvaluator(workbook, evaluator);
	}

	private void parseWorkbookToMapWithEvaluator(Workbook workbook, FormulaEvaluator evaluator) {
		for (Sheet sheet : workbook) {
			HashMap<String, DatamapLineValue<?>> sheetData = new HashMap<>();
			for (Row row : sheet) {
				for (Cell cell : row) {
					switch (cell.getCellType()) {
					case BOOLEAN:
						DatamapLineValue<?> valBool = new DatamapLineValue<Boolean>(cell.getBooleanCellValue());
						sheetData.put(cell.getAddress().formatAsString(), valBool);
						break;
					case STRING:
						DatamapLineValue<?> valStr = new DatamapLineValue<String>(cell.getStringCellValue());
						sheetData.put(cell.getAddress().formatAsString(), valStr);
						break;
					case NUMERIC:
						if (DateUtil.isCellDateFormatted(cell)) {
							GregorianCalendar date = new GregorianCalendar();
							date.setTime(cell.getDateCellValue());
							DatamapLineValue<?> valDate = new DatamapLineValue<GregorianCalendar>(date);
							sheetData.put(cell.getAddress().formatAsString(), valDate);
						} else {
							DatamapLineValue<?> valDateOrNumeric = new DatamapLineValue<Double>(cell.getNumericCellValue());
							sheetData.put(cell.getAddress().formatAsString(), valDateOrNumeric);
						}
						break;
					case FORMULA:
						switch (evaluator.evaluateFormulaCell(cell)) {
						case BOOLEAN:
							DatamapLineValue<?> valFuncBool = new DatamapLineValue<Boolean>(cell.getBooleanCellValue());
							sheetData.put(cell.getAddress().formatAsString(), valFuncBool);
							break;
						case STRING:
							DatamapLineValue<?> valFuncString = new DatamapLineValue<String>(cell.getStringCellValue());
							sheetData.put(cell.getAddress().formatAsString(), valFuncString);
							break;
						case NUMERIC:
							DatamapLineValue<?> valNumericString = new DatamapLineValue<Double>(cell.getNumericCellValue());
							sheetData.put(cell.getAddress().formatAsString(), valNumericString);
							break;
						case ERROR:
							break;
						case FORMULA:
							break;
						case BLANK:
							break;
						default:
							break;
						}
					case ERROR:
						break;
					case BLANK:
						break;
					default:
						DatamapLineValue<?> defaultStr = new DatamapLineValue<String>(cell.getStringCellValue());
						sheetData.put(cell.getAddress().formatAsString(), defaultStr);
					}
				}
			}
			returnObj.getData().put(sheet.getSheetName(), sheetData);
		}
	}

}
