package com.matthewlemon.dbasiktest.parser;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class XLSXSheetData {

    private HashMap<CellReference,String> hm = new HashMap<>();
    private HashMap<String, String> hmStringsAsKeys = new HashMap<>();

    XLSXSheetData(File filePath, String sheetName) throws IOException, InvalidFormatException {

        File f = new File(String.valueOf(filePath));
        Workbook workbook = new XSSFWorkbook(f);
        DataFormatter formatter = new DataFormatter();
        Sheet sheet = workbook.getSheet(sheetName);
        for (Row row : sheet) {
            for (Cell cell : row) {
                CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                this.hm.put(cellRef, formatter.formatCellValue(cell));
                this.hmStringsAsKeys.put(cellRef.formatAsString(), formatter.formatCellValue(cell));
            }
        }
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
    }

    HashMap getDataMapHash() {
        return this.hm;
    }

    HashMap getDataMapHash_StringsAsKeys() {
        return this.hmStringsAsKeys;
    }
}

