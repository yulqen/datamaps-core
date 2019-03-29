package com.matthewlemon.dbasiktest.entities;

import java.util.ArrayList;
import java.util.List;

public class Datamap {

    private String datamapName;

    public Datamap(String datamapName) {
        this.datamapName = datamapName;
    }

    List<DatamapLine> datamapLines = new ArrayList<>();

    public String getName() {
        return datamapName;
    }

    public List<DatamapLine> getDatamapLines() {
        return datamapLines;
    }

    public void setName(String datamapName) {
        this.datamapName = datamapName;
    }

    public void addDatamapLine(DatamapLine datamapLine) {
        datamapLines.add(datamapLine);
    }
}