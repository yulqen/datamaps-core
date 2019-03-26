package com.matthewlemon.dbasiktest.usecases;

import com.matthewlemon.dbasiktest.Context;
import com.matthewlemon.dbasiktest.TestSetup;
import com.matthewlemon.dbasiktest.entities.Datamap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class CreateableDatamapUseCaseShould {

    private CreateableDatamapUseCase useCase;
    private Datamap testDatamap;
    private String TITLE;

    @Before
    public void setUp() {
        TestSetup.setupContext();

        TITLE = "Test Datamap";

        useCase = new CreateableDatamapUseCase();
    }

    @Test
    public void datamapIsCreated() {
        testDatamap = useCase.createDatamap(TITLE);
        assertThat((testDatamap).getName(), is(TITLE));
    }

    @Test
    public void canGetCountOfLinesInDatamap() {
        testDatamap = useCase.createDatamap(TITLE);
        useCase.addLineToDatamap(TITLE, "Key 1", "Sheet 1", "A2");
        int lineCount = useCase.getLineCountFromDatamap(TITLE);
        assertThat(lineCount, is(1));
    }

    @Test
    public void findDatamapWithName() {
        testDatamap = useCase.createDatamap(TITLE);
        Datamap returnedDatamap = Context.datamapGateway.getDatamapWithName("Test Datamap");
        assertThat(returnedDatamap.getName(), is(TITLE));
    }

}
