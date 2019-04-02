package com.matthewlemon.dbasiktest.fixtures;

import java.io.File;

import com.matthewlemon.dbasiktest.TestSetup;
import com.matthewlemon.dbasiktest.entities.Datamap;
import com.matthewlemon.dbasiktest.gateways.DatamapTextType;
import com.matthewlemon.dbasiktest.usecases.excelparser.ExcelParserUseCase;

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
