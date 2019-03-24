package com.matthewlemon.dbasiktest;

import com.matthewlemon.dbasiktest.doubles.InMemoryDatamapGateway;

public class TestSetup {
    public static void setupContext() {
        Context.datamapGateway = new InMemoryDatamapGateway();
    }

    public static void setUpSampleData() {
        setupContext();
    }
}
