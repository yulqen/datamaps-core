package com.matthewlemon.datamaps.core.usecases.excelparser;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.matthewlemon.datamaps.core.entities.PopulatedTemplate;
import com.matthewlemon.datamaps.core.gateways.PopulatedTemplateGateway;

public class ExcelParserUseCase {

	private PopulatedTemplateGateway gateway;

	public ExcelParserUseCase() {
		this.gateway = new PopulatedTemplateGateway();
	}

	public PopulatedTemplate createPopulatedTemplate(File file) throws InvalidFormatException, IOException {
		PopulatedTemplate template = gateway.createPopulatedTemplate(file);
		return template;
	}


}
