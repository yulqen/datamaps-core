package com.matthewlemon.dbasiktest.entities;

import java.util.ArrayList;
import java.util.List;

public class Datamap {

    List<DatamapLine> datamapLines = new ArrayList<>();

    private String datamapName;

    public Datamap(String datamapName) {
        this.datamapName = datamapName;
    }

    public String getName() {
        return datamapName;
    }

    public void setName(String datamapName) {
        this.datamapName = datamapName;
    }

    public void addDatamapLine(DatamapLine datamapLine) {
        datamapLines.add(datamapLine);
    }

    public List<DatamapLine> getDatamapLines() {
        return datamapLines;
    }
}