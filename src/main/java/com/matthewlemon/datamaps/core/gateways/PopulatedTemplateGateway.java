package com.matthewlemon.datamaps.core.gateways;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.matthewlemon.datamaps.core.entities.PopulatedTemplate;

public class PopulatedTemplateGateway implements ImportFileGateway {

	@Override
	public PopulatedTemplate createPopulatedTemplate(File file) {
		PopulatedTemplate populatedTemplate;
		try {
			populatedTemplate = new PopulatedTemplate(file);
			return populatedTemplate;
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
