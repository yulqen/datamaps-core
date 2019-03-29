package com.matthewlemon.dbasiktest.gateways;

import com.matthewlemon.dbasiktest.entities.CSVFile;
import com.matthewlemon.dbasiktest.entities.Datamap;

import java.util.List;

public interface DatamapGateway {

    Datamap createDatamap(String datamapName);

    boolean datamapExists(String datamapName);

    int datamapCount();

    void addLineToDatamap(String datamapName, String key, String sheetName, String cellRef);

    Datamap getDatamap(String test_datamap);

    List getDataLinesFor(String datamapName);

    void addDataToDatamapWithCSV(String datamapName, CSVFile csvFile);
}
