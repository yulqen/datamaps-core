package com.matthewlemon.dbasiktest.usecases;

import com.matthewlemon.dbasiktest.Context;
import com.matthewlemon.dbasiktest.TestSetup;
import com.matthewlemon.dbasiktest.entities.Datamap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DatamapMechanicsTests {

    private DatamapMechanicsUseCase useCase;
    private String datamapName;
    private Datamap datamap;

    @Before
    public void setUp() {
        TestSetup.setupContext();
        useCase = new DatamapMechanicsUseCase();
        datamapName = "Test Datamap";
        mockedDatamap = mock(Datamap.class);
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
