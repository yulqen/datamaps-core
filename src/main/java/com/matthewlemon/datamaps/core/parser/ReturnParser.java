package com.matthewlemon.datamaps.core.parser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.entities.DatamapType;
import com.matthewlemon.datamaps.core.entities.InMemoryReturn;
import com.matthewlemon.datamaps.core.exceptions.CellValueNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.IncorrectCellTypeException;

public class ReturnParser {

	private InMemoryReturn returnObj;
	@SuppressWarnings("unused")
	private Datamap datamap;

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

	public Object getCellValueFromSheetWithTypeChecking(String sheetName, DatamapLine datamapLine)
			throws CellValueNotFoundException, IncorrectCellTypeException {
		DatamapType enumType = datamapLine.getDatamapType();

		Class<?> classDeclaredInDatamapLine = datamapLine.getDatamapType().getType();
		Class<?> classOfCellValue = this.returnObj.getCellValue(sheetName, datamapLine).getType();
		if (!classDeclaredInDatamapLine.equals(classOfCellValue)) {
			throw new IncorrectCellTypeException("Value at cell " + datamapLine.getCellRef() + " on sheet " + sheetName
					+ " is not a " + enumType.name() + " type");
		}
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
		DataFormatter formatter = new DataFormatter();
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
						if (DateUtil.isCellDateFormatted(cell)) {
							DatamapValue<?> valDateOrNumeric = new DatamapValue<String>(
									formatter.formatCellValue(cell));
							sheetData.put(cell.getAddress().formatAsString(), valDateOrNumeric);
						} else {
							DatamapValue<?> valDateOrNumeric = new DatamapValue<Double>(cell.getNumericCellValue());
							sheetData.put(cell.getAddress().formatAsString(), valDateOrNumeric);
						}
						break;
					case FORMULA:
						switch (evaluator.evaluateFormulaCell(cell)) {
						case BOOLEAN:
							DatamapValue<?> valFuncBool = new DatamapValue<Boolean>(cell.getBooleanCellValue());
							sheetData.put(cell.getAddress().formatAsString(), valFuncBool);
							break;
						case STRING:
							DatamapValue<?> valFuncString = new DatamapValue<String>(cell.getStringCellValue());
							sheetData.put(cell.getAddress().formatAsString(), valFuncString);
							break;
						case NUMERIC:
							DatamapValue<?> valNumericString = new DatamapValue<Double>(cell.getNumericCellValue());
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
						DatamapValue<?> defaultStr = new DatamapValue<String>(cell.getStringCellValue());
						sheetData.put(cell.getAddress().formatAsString(), defaultStr);
					}
				}
			}
			returnObj.getData().put(sheet.getSheetName(), sheetData);
		}
	}
}
