package com.matthewlemon.dbasiktest.gateways;

import com.matthewlemon.dbasiktest.entities.Datamap;

public interface DatamapGateway {

    Datamap create(String datamapName);

    boolean getDatamap(String datamapName);

    boolean datamapExists(String datamapName);

    int datamapCount();
}
