package com.matthewlemon.dbasiktest.gateways;

public interface DatamapGateway {

    void create(String datamapName);

    boolean getDatamap(String datamapName);
}
