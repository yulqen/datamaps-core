package com.matthewlemon.datamaps.core.gateways;

import java.io.File;

import com.matthewlemon.datamaps.core.entities.PopulatedTemplate;

// TODO - this isn't right for a gateway: not generic
public interface ImportFileGateway {

	PopulatedTemplate createPopulatedTemplate(File file);

}
