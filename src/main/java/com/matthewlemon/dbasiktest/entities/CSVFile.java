package com.matthewlemon.dbasiktest.entities;

import java.io.File;

public class CSVFile {

    private File sourceFile;

	public CSVFile(String filePath) {
		File tmpfile = new File(filePath);
		sourceFile = tmpfile.getAbsoluteFile();
    }

    public CSVFile(File fileName) {
    	sourceFile = fileName;
    }

    public File getFile() {
    	return sourceFile;
    }
}
