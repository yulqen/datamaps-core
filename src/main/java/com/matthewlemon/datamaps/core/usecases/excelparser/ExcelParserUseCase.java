package com.matthewlemon.datamaps.core.usecases.excelparser;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.matthewlemon.datamaps.core.entities.PopulatedTemplate;

public class ExcelParserUseCase {

	public PopulatedTemplate createPopulatedTemplate(File file) throws InvalidFormatException, IOException {
		PopulatedTemplate template = new PopulatedTemplate(file);
		return template;
	}


}
