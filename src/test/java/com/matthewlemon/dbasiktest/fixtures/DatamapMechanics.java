package com.matthewlemon.dbasiktest.fixtures;

import com.matthewlemon.dbasiktest.TestSetup;
import com.matthewlemon.dbasiktest.usecases.DatamapMechanicsUseCase;

public class DatamapMechanics {

    private DatamapMechanicsUseCase usecase = new DatamapMechanicsUseCase();

    public DatamapMechanics() {
        TestSetup.setupContext();
    }

    public int datamapRows(String name) {
        return 0;
    }

    public boolean createDatamap(String name) {
        usecase.createDatamap(name);
        return false;
    }

    public boolean datamapExists(String name) {
        return false;
    }
}
