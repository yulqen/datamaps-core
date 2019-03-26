package com.matthewlemon.dbasiktest.doubles;

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
    public boolean getDatamap(String datamapName) {
        return false;
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
        Datamap datamap = getDatamapWithName(datamapName);
        datamap.addDatamapLine(datamapLine);
    }

    @Override
    public Datamap getDatamapWithName(String datamapName) {
        try {
            for (Datamap datamap : dataMaps) {
                if (datamap.getName().equals(datamapName)) {
                    return datamap;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<DatamapLine> getDataLinesFor(String datamapName) {
        Datamap datamap = getDatamapWithName(datamapName);
        return datamap.getDatamapLines();
    }
}