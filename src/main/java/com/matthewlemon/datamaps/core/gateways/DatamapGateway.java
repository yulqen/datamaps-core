package com.matthewlemon.datamaps.core.gateways;

import java.util.List;

import com.matthewlemon.datamaps.core.entities.CSVFile;
import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.exceptions.DatamapLineNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DatamapNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;
import com.matthewlemon.datamaps.core.parser.DatamapType;

public interface DatamapGateway {

    Datamap createDatamap(String datamapName) throws DuplicateDatamapException;

    boolean datamapExists(String datamapName);

    int datamapCount();

    Datamap getDatamap(String test_datamap) throws DatamapNotFoundException;

    List<DatamapLine> getDataLinesFor(String datamapName) throws DatamapNotFoundException;

    void addDataToDatamapWithCSV(String datamapName, CSVFile csvFile) throws DatamapNotFoundException;

    void deleteAllLinesIn(String test_datamap);

	void addLineToDatamap(String datamapName, String key, String sheetName, String cellRef, DatamapType type) throws DatamapNotFoundException;

	void addLineToDatamap(String datamapName, String key, String sheetName, String cellRef) throws DatamapNotFoundException;

	DatamapLine getDatamapLineFrom(String datamapName, String key) throws DatamapLineNotFoundException, DatamapNotFoundException;
}
