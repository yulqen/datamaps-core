package com.matthewlemon.dbasiktest.gateways;

import com.matthewlemon.dbasiktest.entities.CSVFile;
import com.matthewlemon.dbasiktest.entities.Datamap;
import com.matthewlemon.dbasiktest.entities.DatamapLine;
import com.matthewlemon.dbasiktest.exceptions.DuplicateDatamapException;

import java.util.List;

public interface DatamapGateway {

    Datamap createDatamap(String datamapName) throws DuplicateDatamapException;

    boolean datamapExists(String datamapName);

    int datamapCount();

    Datamap getDatamap(String test_datamap);

    List<DatamapLine> getDataLinesFor(String datamapName);

    void addDataToDatamapWithCSV(String datamapName, CSVFile csvFile);

    void deleteAllLinesIn(String test_datamap);

	void addLineToDatamap(String datamapName, String key, String sheetName, String cellRef,
			DatamapType type);
}
