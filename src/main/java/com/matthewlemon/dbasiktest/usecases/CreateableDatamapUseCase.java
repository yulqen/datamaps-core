package com.matthewlemon.dbasiktest.usecases;

import com.matthewlemon.dbasiktest.entities.Datamap;

public class CreateableDatamapUseCase {

    public Datamap createDatamap(String datamapName) {
        Datamap datamap = new Datamap(datamapName);
        return datamap;
    }

    public void addLineToDatamap(String datamapName, String key, String sheetName, String cellRef) {
    }

    public int getLineCountFromDatamap(String test_datamap) {
        return 0;
    }
}
