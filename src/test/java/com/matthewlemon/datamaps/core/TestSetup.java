package com.matthewlemon.datamaps.core;

import com.matthewlemon.datamaps.core.Context;
import com.matthewlemon.datamaps.core.gateways.InMemoryDatamapGateway;
import com.matthewlemon.datamaps.core.gateways.FileDatamapGateway;

public class TestSetup {
	public static void setupContext() {
		Context.datamapGateway = new InMemoryDatamapGateway();
		Context.fileDatamapGateway = new FileDatamapGateway();
	}

	public static void setUpSampleData() {
		setupContext();
	}
}
