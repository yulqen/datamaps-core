package com.matthewlemon.datamaps.core;

import com.matthewlemon.datamaps.core.repositories.InMemoryDatamapRepository;
import com.matthewlemon.datamaps.core.repositories.FileDatamapRepository;

public class TestSetup {
	public static void setupContext() {
		Context.datamapRepository = new InMemoryDatamapRepository();
		Context.fileDatamapRepository = new FileDatamapRepository();
	}

	public static void setUpSampleData() {
		setupContext();
	}
}
