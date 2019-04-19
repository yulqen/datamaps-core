package com.matthewlemon.datamaps.core.parser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXSheetData {

    private HashMap<CellAddress,String> dataCellRefAsKey = new HashMap<>();
    private HashMap<String, String> dataStringAsKey = new HashMap<>();

    public XLSXSheetData(File filePath, String sheetName) throws IOException, InvalidFormatException {

        File f = new File(String.valueOf(filePath));
        Workbook workbook = new XSSFWorkbook(f);
        DataFormatter formatter = new DataFormatter();
        Sheet sheet = workbook.getSheet(sheetName);
        for (Row row : sheet) {
            for (Cell cell : row) {
                CellAddress cellRef = new CellAddress(row.getRowNum(), cell.getColumnIndex());
                this.dataCellRefAsKey.put(cellRef, formatter.formatCellValue(cell));
                this.dataStringAsKey.put(cellRef.formatAsString(), formatter.formatCellValue(cell));
            }
        }
        workbook.close();
    }

    public HashMap<CellAddress, String> getDataMapHash() {
        return this.dataCellRefAsKey;
    }

    public HashMap<String, String> getDataMapHashStringsAsKeys() {
        return this.dataStringAsKey;
    }
}

