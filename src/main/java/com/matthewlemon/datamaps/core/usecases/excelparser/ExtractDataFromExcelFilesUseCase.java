/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.matthewlemon.datamaps.core.usecases.excelparser;

import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.entities.InMemoryReturn;
import com.matthewlemon.datamaps.core.parser.ReturnParser;
import java.io.File;
import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;

/**
 *
 * @author lemon
 */
class ExtractDataFromExcelFilesUseCase {

	private ReturnParser parser;

	public ExtractDataFromExcelFilesUseCase() {
	}

	InMemoryReturn extractDataFromFileUsingDatamap(String returnName, File testFile, Datamap testDatamap) throws EncryptedDocumentException, IOException {
		InMemoryReturn rtn = new InMemoryReturn(returnName);
		parser = new ReturnParser(rtn);
		parser.parse(testFile);
		return rtn;
	}
}
