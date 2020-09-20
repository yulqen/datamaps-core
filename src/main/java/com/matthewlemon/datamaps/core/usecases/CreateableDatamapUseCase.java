package com.matthewlemon.datamaps.core.usecases;

import com.matthewlemon.datamaps.core.Context;
import com.matthewlemon.datamaps.core.entities.CSVFile;
import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.exceptions.DatamapNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;
import com.matthewlemon.datamaps.core.parser.DatamapLineType;

public class CreateableDatamapUseCase {

    public void addCSVDataToDatamap(String datamapName, CSVFile csvFile) throws DatamapNotFoundException {
        Context.datamapRepository.addDataToDatamapWithCSV(datamapName, csvFile);
    }

    public void addLineToDatamap(String datamapName, String key,
                                 String sheetName, String cellRef, DatamapLineType type) throws DatamapNotFoundException {
        Context.datamapRepository.addLineToDatamap(datamapName, key, sheetName, cellRef, type);
    }


    public Datamap createDatamap(String datamapName) throws DuplicateDatamapException {
        Datamap datamap = Context.datamapRepository.createDatamap(datamapName);
         return datamap;
    }

    public Datamap getDatamap(String datamapName) throws DatamapNotFoundException {
        return Context.datamapRepository.getDatamap(datamapName);
    }

    public int getLineCountFromDatamap(String datamapName) throws DatamapNotFoundException {
        Datamap datamap = Context.datamapRepository.getDatamap(datamapName);
        return datamap.getDatamapLines().size();
    }
}
