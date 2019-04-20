package com.matthewlemon.datamaps.core.gateways;

import java.util.ArrayList;
import java.util.List;

import com.matthewlemon.datamaps.core.entities.CSVFile;
import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.entities.DatamapType;
import com.matthewlemon.datamaps.core.exceptions.DatamapLineNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DatamapNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;

public class InMemoryDatamapGateway implements DatamapGateway {

	private List<Datamap> dataMaps = new ArrayList<>();

	@Override
	public void addDataToDatamapWithCSV(String datamapName, CSVFile csvFile) throws DatamapNotFoundException {
		Datamap datamap = getDatamap(datamapName);
		datamap.readCSV(csvFile.getFile());
		dataMaps.add(datamap);
	}

	@Override
	public void addLineToDatamap(String datamapName, String key, String sheetName, String cellRef)
			throws DatamapNotFoundException {
		DatamapLine datamapLine = new DatamapLine(key, sheetName, cellRef);
		Datamap datamap = getDatamap(datamapName);
		datamap.addDatamapLine(datamapLine);
	}

	@Override
	public void addLineToDatamap(String datamapName, String key, String sheetName, String cellRef, DatamapType type)
			throws DatamapNotFoundException {
		DatamapLine datamapLine = new DatamapLine(key, sheetName, cellRef, type);
		Datamap datamap = getDatamap(datamapName);
		datamap.addDatamapLine(datamapLine);
	}

	@Override
	public Datamap createDatamap(String datamapName) throws DuplicateDatamapException {
		for (Datamap datamap : dataMaps) {
			if (datamap.getName() == datamapName) {
				throw new DuplicateDatamapException("There is already a Datamap with the name " + datamapName);
			}
		}
		dataMaps.add(new Datamap(datamapName));
		return new Datamap(datamapName);
	}

	@Override
	public int datamapCount() {
		return dataMaps.size();
	}

	@Override
	public boolean datamapExists(String datamapName) {
		for (Datamap dataMap : dataMaps) {
			if (dataMap.getName().equals(datamapName)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void deleteAllDatamaps() throws Exception {
		try {
			this.dataMaps.clear();
		} catch (NullPointerException e) {
			throw new Exception("There are no Datamaps to delete.");
		}
	}

	@Override
	public void deleteAllLinesIn(String datamapName) {
		Datamap datamap = new Datamap(datamapName);
		datamap.deleteAllLines();
	}

	public List<DatamapLine> getDataLinesFor(Datamap datamap) {
		return datamap.getDatamapLines();
	}

	@Override
	public List<DatamapLine> getDataLinesFor(String datamapName) throws DatamapNotFoundException {
		Datamap datamap = getDatamap(datamapName);
		return datamap.getDatamapLines();
	}

	@Override
	public Datamap getDatamap(String datamapName) throws DatamapNotFoundException {
		for (Datamap datamap : dataMaps) {
			if (datamap.getName().equals(datamapName)) {
				return datamap;
			}
		}
		throw new DatamapNotFoundException("Datamap " + datamapName + " cannot be found.");
	}

	@Override
	public DatamapLine getDatamapLineFrom(String datamapName, String key)
			throws DatamapLineNotFoundException, DatamapNotFoundException {
		Datamap datamap = getDatamap(datamapName);
		for (DatamapLine datamapLine : datamap.getDatamapLines()) {
			if (datamapLine.getKey().equals(key)) {
				return datamapLine;
			}
		}
		throw new DatamapLineNotFoundException("Cannot find datamapline with key of " + key);
	}
}
