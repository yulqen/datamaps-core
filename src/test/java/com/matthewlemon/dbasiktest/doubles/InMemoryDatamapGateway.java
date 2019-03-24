package com.matthewlemon.dbasiktest.doubles;

import com.matthewlemon.dbasiktest.gateways.DatamapGateway;

public class InMemoryDatamapGateway implements DatamapGateway {

    @Override
    public void create(String datamapName) {

    }

    @Override
    public boolean getDatamap(String datamapName) {
        return false;
    }
}
