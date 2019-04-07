package com.matthewlemon.datamaps.core.parser;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class XLSXSheetData {

    private HashMap<CellReference,String> dataCellRefAsKey = new HashMap<>();
    private HashMap<String, String> dataStringAsKey = new HashMap<>();

    public XLSXSheetData(File filePath, String sheetName) throws IOException, InvalidFormatException {

        File f = new File(String.valueOf(filePath));
        Workbook workbook = new XSSFWorkbook(f);
        DataFormatter formatter = new DataFormatter();
        Sheet sheet = workbook.getSheet(sheetName);
        for (Row row : sheet) {
            for (Cell cell : row) {
                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                this.dataCellRefAsKey.put(cellRef, formatter.formatCellValue(cell));
                this.dataStringAsKey.put(cellRef.formatAsString(), formatter.formatCellValue(cell));
            }
        }
        workbook.close();
    }

    private static void readXLSXFileSheet(File filePath, String sheetName) throws IOException, InvalidFormatException {
        File f = new File(String.valueOf(filePath));
        Workbook workbook = new XSSFWorkbook(f);
        DataFormatter formatter = new DataFormatter();
        Sheet sheet = workbook.getSheet(sheetName);
        for (Row row : sheet) {
            for (Cell cell : row) {
                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                System.out.print(cellRef.formatAsString());
                System.out.print(" - ");

                String text = formatter.formatCellValue(cell);
                System.out.println(text);
            }
        }
        workbook.close();
    }

    public HashMap getDataMapHash() {
        return this.dataCellRefAsKey;
    }

    public HashMap getDataMapHashStringsAsKeys() {
        return this.dataStringAsKey;
    }
}

