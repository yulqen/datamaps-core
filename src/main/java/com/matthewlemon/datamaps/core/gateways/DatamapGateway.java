package com.matthewlemon.datamaps.core.gateways;

import java.util.List;

import com.matthewlemon.datamaps.core.entities.CSVFile;
import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.entities.DatamapType;
import com.matthewlemon.datamaps.core.exceptions.DatamapLineNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DatamapNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;

public interface DatamapGateway {

    void addDataToDatamapWithCSV(String datamapName, CSVFile csvFile) throws DatamapNotFoundException;

    void addLineToDatamap(String datamapName, String key, String sheetName, String cellRef, DatamapType text) throws DatamapNotFoundException;

    Datamap createDatamap(String datamapName) throws DuplicateDatamapException;

    int datamapCount();

    boolean datamapExists(String datamapName);

    void deleteAllDatamaps() throws Exception;

    void deleteAllLinesIn(String test_datamap);

	List<DatamapLine> getDataLinesFor(String datamapName) throws DatamapNotFoundException;

	Datamap getDatamap(String test_datamap) throws DatamapNotFoundException;

	DatamapLine getDatamapLineFrom(String datamapName, String key) throws DatamapLineNotFoundException, DatamapNotFoundException;

}
