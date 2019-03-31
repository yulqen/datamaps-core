package com.matthewlemon.dbasiktest.doubles;

import com.matthewlemon.dbasiktest.entities.CSVFile;
import com.matthewlemon.dbasiktest.entities.Datamap;
import com.matthewlemon.dbasiktest.entities.DatamapLine;
import com.matthewlemon.dbasiktest.gateways.DatamapGateway;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatamapGateway implements DatamapGateway {

    private List<Datamap> dataMaps = new ArrayList<>();

    @Override
    public Datamap createDatamap(String datamapName) {
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
    public void addLineToDatamap(String datamapName, String key, String sheetName, String cellRef) {
        DatamapLine datamapLine = new DatamapLine(key, sheetName, cellRef);
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