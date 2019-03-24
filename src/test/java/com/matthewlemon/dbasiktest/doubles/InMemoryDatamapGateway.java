package com.matthewlemon.dbasiktest.doubles;

import com.matthewlemon.dbasiktest.entities.Datamap;
import com.matthewlemon.dbasiktest.gateways.DatamapGateway;

public class InMemoryDatamapGateway implements DatamapGateway {

    @Override
    public Datamap create(String datamapName) {
        Datamap datamap = new Datamap(datamapName);
        return datamap;
    }

    @Override
    public boolean getDatamap(String datamapName) {
        return false;
    }
}
