package com.matthewlemon.dbasiktest.fixtures;

import com.matthewlemon.dbasiktest.TestSetup;
import com.matthewlemon.dbasiktest.entities.Datamap;
import com.matthewlemon.dbasiktest.usecases.DatamapMechanicsUseCase;

public class DatamapMechanics {

    private DatamapMechanicsUseCase useCase = new DatamapMechanicsUseCase();

    public DatamapMechanics() {
        TestSetup.setupContext();
    }

    public int datamapCount(String datamapName) {
        return 0;
    }

    public boolean createDatamap(String datamapName) {
        Datamap datamap = useCase.createDatamap(datamapName);
        return true;
    }

    public boolean datamapExists(String datamapName) {
        return useCase.datamapExists(datamapName);
    }
}
