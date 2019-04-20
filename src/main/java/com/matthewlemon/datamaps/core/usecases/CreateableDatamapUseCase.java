package com.matthewlemon.datamaps.core.usecases;

import com.matthewlemon.datamaps.core.Context;
import com.matthewlemon.datamaps.core.entities.CSVFile;
import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.exceptions.DatamapNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;

public class CreateableDatamapUseCase {

    public Datamap createDatamap(String datamapName) throws DuplicateDatamapException {
        Datamap datamap = Context.datamapGateway.createDatamap(datamapName);
         return datamap;
    }

    public void addLineToDatamap(String datamapName, String key,
                                 String sheetName, String cellRef) throws DatamapNotFoundException {
        Context.datamapGateway.addLineToDatamap(datamapName, key, sheetName, cellRef);
    }


    public int getLineCountFromDatamap(String datamapName) throws DatamapNotFoundException {
        Datamap datamap = Context.datamapGateway.getDatamap(datamapName);
        return datamap.getDatamapLines().size();
    }

    public Datamap getDatamap(String datamapName) throws DatamapNotFoundException {
        return Context.datamapGateway.getDatamap(datamapName);
    }

    public void addCSVDataToDatamap(String datamapName, CSVFile csvFile) throws DatamapNotFoundException {
        Context.datamapGateway.addDataToDatamapWithCSV(datamapName, csvFile);
    }
}
