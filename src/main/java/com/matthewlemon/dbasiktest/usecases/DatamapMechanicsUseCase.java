package com.matthewlemon.dbasiktest.usecases;

import com.matthewlemon.dbasiktest.Context;
import com.matthewlemon.dbasiktest.entities.Datamap;

public class DatamapMechanicsUseCase {

    public Datamap createDatamap(String datamapName) {
        Datamap datamap = Context.datamapGateway.create(datamapName);
        return datamap;
    }

    public boolean datamapExists(String datamapName) {
        return Context.datamapGateway.datamapExists(datamapName);
    }
}
