package com.matthewlemon.dbasiktest.parser;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import static org.junit.Assert.*;

public class XLSXParserTest {

    // how to create a tmp directory using junit
    // https://junit.org/junit4/javadoc/latest/index.html
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    private File testFile;
    private final String sheetName = "Sheet1";

    @Before
    public void setUp() throws IOException {
        // input source file
        ClassLoader classLoader = getClass().getClassLoader();
        testFile = new File(classLoader.getResource("files/test.xlsx").getFile());

        // output source file
        File outputFile = tempFolder.newFile("output.xlsx");
        Workbook workbook = new XSSFWorkbook();
        OutputStream fileOutputStream = new FileOutputStream(outputFile);
        Sheet sheet = workbook.createSheet("new sheet");

        Row row1 = sheet.createRow(0);
        Cell r1c1 = row1.createCell(0);
        r1c1.setCellValue("Project/Programme Name");

        Cell r1c2 = row1.createCell(1);
        r1c2.setCellValue("Crumpton Colliery");

        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    // Here we are not testing our own code
    @Test
    public void getCellValueWithCellReference() throws IOException, InvalidFormatException {
        XLSXSheetData sheetData = new XLSXSheetData(testFile, sheetName);
        Map dataFromSheet = sheetData.getDataMapHash();
        assertEquals(dataFromSheet.get(new CellReference("B1")), "Useless Project");
    }

    // Here we are not testing our own code
    @Test
    public void getCellValueWithString() throws IOException, InvalidFormatException {
        XLSXSheetData sheetData = new XLSXSheetData(testFile, sheetName);
        Map dataFromSheet = sheetData.getDataMapHash_StringsAsKeys();
        assertEquals(dataFromSheet.get("B1"), "Useless Project");
    }
}
