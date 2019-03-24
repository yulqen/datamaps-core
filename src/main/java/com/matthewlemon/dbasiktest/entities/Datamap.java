package com.matthewlemon.dbasiktest.entities;

public class Datamap {

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
}
