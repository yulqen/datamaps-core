package com.matthewlemon.datamaps.core.doubles;

import java.util.ArrayList;
import java.util.List;

import com.matthewlemon.datamaps.core.entities.CSVFile;
import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;
import com.matthewlemon.datamaps.core.gateways.DatamapGateway;
import com.matthewlemon.datamaps.core.gateways.DatamapType;

public class InMemoryDatamapGateway implements DatamapGateway {

    private List<Datamap> dataMaps = new ArrayList<>();

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
    public boolean datamapExists(String datamapName) {
        for (Datamap dataMap : dataMaps) {
            if (dataMap.getName().equals(datamapName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int datamapCount() {
        return dataMaps.size();
    }

    @Override
    public void addLineToDatamap(String datamapName, String key, String sheetName, String cellRef, DatamapType type) {
        DatamapLine datamapLine = new DatamapLine(key, sheetName, cellRef, type);
        Datamap datamap = getDatamap(datamapName);
        datamap.addDatamapLine(datamapLine);
    }

    @Override
    public Datamap getDatamap(String datamapName) {
        Datamap nullDm = null;
        for (Datamap datamap : dataMaps) {
            if (datamap.getName().equals(datamapName)) {
                return datamap;
            }
        }
        // TODO we should test this
        return nullDm;
    }

    @Override
    public List<DatamapLine> getDataLinesFor(String datamapName) {
        Datamap datamap = getDatamap(datamapName);
        return datamap.getDatamapLines();
    }

    public List<DatamapLine> getDataLinesFor(Datamap datamap) {
        return datamap.getDatamapLines();
    }

    @Override
    public void addDataToDatamapWithCSV(String datamapName, CSVFile csvFile) {
        Datamap datamap = getDatamap(datamapName);
        datamap.readCSV(csvFile.getFile());
        dataMaps.add(datamap);
    }

    @Override
    public void deleteAllLinesIn(String datamapName) {
        Datamap datamap = new Datamap(datamapName);
        datamap.deleteAllLines();
    }
}
