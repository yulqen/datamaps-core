package com.matthewlemon.dbasiktest.usecases;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateableDatamapUseCaseShould {

    @Test
    public void createDatamap() {
        CreateableDatamapUseCase useCase = new CreateableDatamapUseCase();
        assertThat(useCase.createDatamap("Test Datamap").getName(), is("Test Datamap"));
    }

    @Test
    public void addLineToDatamap() {
        CreateableDatamapUseCase useCase = new CreateableDatamapUseCase();
        useCase.createDatamap("Test Datamap");
        useCase.addLineToDatamap("Test Datamap", "Key 1", "Sheet 1", "A2");
        assertThat(useCase.getLineCountFromDatamap("Test Datamap"), is(1));
    }

}
