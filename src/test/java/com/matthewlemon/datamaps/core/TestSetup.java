package com.matthewlemon.datamaps.core;

import com.matthewlemon.datamaps.core.Context;
import com.matthewlemon.datamaps.core.doubles.InMemoryDatamapGateway;
import com.matthewlemon.datamaps.core.gateways.PopulatedTemplateGateway;

public class TestSetup {
    public static void setupContext() {
        Context.datamapGateway = new InMemoryDatamapGateway();
        Context.populatedTemplateGateway = new PopulatedTemplateGateway();
    }

    public static void setUpSampleData() {
        setupContext();
    }
}
