package com.matthewlemon.dbasiktest.usecases;

import com.matthewlemon.dbasiktest.Context;
import com.matthewlemon.dbasiktest.TestSetup;
import com.matthewlemon.dbasiktest.entities.Datamap;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateableDatamapUseCaseShould {

    @Before
    public void setUp() {
        TestSetup.setupContext();
    }

    @Test
    public void createDatamap() {
        CreateableDatamapUseCase useCase = new CreateableDatamapUseCase();
        assertThat(useCase.createDatamap("Test Datamap").getName(), is("Test Datamap"));
    }

    @Test
    @Ignore
    public void addLineToDatamap() {
        CreateableDatamapUseCase useCase = new CreateableDatamapUseCase();
        useCase.createDatamap("Test Datamap");
        useCase.addLineToDatamap("Test Datamap", "Key 1", "Sheet 1", "A2");
        assertThat(useCase.getLineCountFromDatamap("Test Datamap"), is(1));
    }

    @Test
    public void findDatamapWithName() {
        CreateableDatamapUseCase useCase = new CreateableDatamapUseCase();
        useCase.createDatamap("Test Datamap");
        Datamap returnedDatamap = Context.datamapGateway.getDatamapWithName("Test Datamap");
        assertThat(returnedDatamap.getName(), is("Test Datamap"));
    }

}
