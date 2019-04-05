package com.matthewlemon.datamaps.core.gateways;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.matthewlemon.datamaps.core.entities.PopulatedTemplate;
import com.matthewlemon.datamaps.core.parser.XLSXSheetData;

public class PopulatedTemplateGateway implements ImportFileGateway {

	private void importDataFromFile(PopulatedTemplate template, File file) {
		Workbook workbook;
		try {
			workbook = new XSSFWorkbook(file);
			for (Sheet sheet : workbook) {
				String sheetName = sheet.getSheetName();
				try {
					extractAndSave(template, file, sheetName);
				} catch (InvalidFormatException | IOException e) {
					e.printStackTrace();
				}
			}
		} catch (InvalidFormatException ife) {
			ife.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	private void extractAndSave(PopulatedTemplate template, File file, String sheetName)
			throws IOException, InvalidFormatException {
		XLSXSheetData data;
		data = new XLSXSheetData(file, sheetName);
		template.setSheetData(sheetName, data.getDataMapHashStringsAsKeys());
	}

	@Override
	public PopulatedTemplate createPopulatedTemplate(File file) {
		PopulatedTemplate populatedTemplate;
		populatedTemplate = new PopulatedTemplate();
		this.importDataFromFile(populatedTemplate, file);
		return populatedTemplate;
	}
}
