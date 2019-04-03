package com.matthewlemon.datamaps.core.fixtures;

import java.io.File;

import com.matthewlemon.datamaps.core.TestSetup;
import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.gateways.DatamapTextType;
import com.matthewlemon.datamaps.core.usecases.excelparser.ExcelParserUseCase;

public class ExcelParserFixture {
	
	private ExcelParserUseCase useCase;
	
    public void setUpFixture() {
        this.useCase = new ExcelParserUseCase();
        TestSetup.setupContext();
    }

	public boolean userCanSetUpPopulatedTemplateWithName(File testFile) {
		return false;
	}

	public Datamap userCreatesDatamapWithName(String string) {
		return null;
	}

	public boolean userAddsSingleLineOfDataToDatamap(String datamapName, String key, String sheet, String cellRef,
			DatamapTextType datamapTextType) {
		return false;
	}

	public boolean userCanSetUpNewReturn(String string) {
		return false;
	}
}
