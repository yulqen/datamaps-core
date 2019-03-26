package com.matthewlemon.dbasiktest.usecases;

import com.matthewlemon.dbasiktest.Context;
import com.matthewlemon.dbasiktest.entities.Datamap;

public class CreateableDatamapUseCase {

    public Datamap createDatamap(String datamapName) {
        Datamap datamap = Context.datamapGateway.create(datamapName);
        return datamap;
    }

    public void addLineToDatamap(String datamapName, String key,
                                 String sheetName, String cellRef) {
        Context.datamapGateway.addLineToDatamap(datamapName, key, sheetName, cellRef);
    }

    public int getLineCountFromDatamap(String test_datamap) {
        return 0;
    }
}
