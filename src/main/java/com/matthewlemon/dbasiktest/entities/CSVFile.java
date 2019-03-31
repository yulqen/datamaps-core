package com.matthewlemon.dbasiktest.entities;

import java.io.File;

public class CSVFile {

    private File file;

	public CSVFile(String filePath) {
		File tmpfile = new File(filePath);
		file = tmpfile.getAbsoluteFile();
    }

    public CSVFile(File fileName) {
    	file = fileName;
    }

    public File getFile() {
    	return file;
    }
}
