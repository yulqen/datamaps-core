package com.matthewlemon.dbasiktest.usecases;

import com.matthewlemon.dbasiktest.Context;
import com.matthewlemon.dbasiktest.entities.CSVFile;
import com.matthewlemon.dbasiktest.entities.Datamap;
import com.matthewlemon.dbasiktest.exceptions.DuplicateDatamapException;
import com.matthewlemon.dbasiktest.gateways.DatamapType;

public class CreateableDatamapUseCase {

    public Datamap createDatamap(String datamapName) throws DuplicateDatamapException {
        Datamap datamap = Context.datamapGateway.createDatamap(datamapName);
         return datamap;
    }

    public void addLineToDatamap(String datamapName, String key,
                                 String sheetName, String cellRef, DatamapType type) {
        Context.datamapGateway.addLineToDatamap(datamapName, key, sheetName, cellRef, type);
    }


    public int getLineCountFromDatamap(String datamapName) {
        Datamap datamap = Context.datamapGateway.getDatamap(datamapName);
        return datamap.getDatamapLines().size();
    }

    public Datamap getDatamap(String datamapName) {
        return Context.datamapGateway.getDatamap(datamapName);
    }

    public void addCSVDataToDatamap(String datamapName, CSVFile csvFile) {
        Context.datamapGateway.addDataToDatamapWithCSV(datamapName, csvFile);
    }
}
