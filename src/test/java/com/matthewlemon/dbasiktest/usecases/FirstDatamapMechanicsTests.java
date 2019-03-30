package com.matthewlemon.dbasiktest.usecases;

import com.matthewlemon.dbasiktest.Context;
import com.matthewlemon.dbasiktest.TestSetup;
import com.matthewlemon.dbasiktest.entities.Datamap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FirstDatamapMechanicsTests {

    private DatamapMechanicsUseCase useCase;
    private String datamapName;
    private Datamap datamap;

    @Before
    public void setUp() {
        TestSetup.setupContext();
        useCase = new DatamapMechanicsUseCase();
        datamapName = "Test Datamap";
    }

    @Test
    public void canCreateNewDatamap() {
        datamap = useCase.createDatamap(datamapName);
        assertEquals(datamap.getName(), "Test Datamap");
    }

    @Test
    public void datamapExists() {
        datamap = useCase.createDatamap(datamapName);
        assertTrue(Context.datamapGateway.datamapExists(datamapName));
    }

    @Test
    public void countDataMaps() {
        datamap = useCase.createDatamap(datamapName);
        assertTrue(Context.datamapGateway.datamapCount() == 1);
    }
}
