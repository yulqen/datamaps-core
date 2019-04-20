package com.matthewlemon.datamaps.core;

import com.matthewlemon.datamaps.core.Context;
import com.matthewlemon.datamaps.core.gateways.InMemoryDatamapGateway;

public class TestSetup {
    public static void setupContext() {
        Context.datamapGateway = new InMemoryDatamapGateway();
    }

    public static void setUpSampleData() {
        setupContext();
    }
}
