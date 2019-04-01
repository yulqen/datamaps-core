package com.matthewlemon.dbasiktest.usecases.creatingdatamaps;

import com.matthewlemon.dbasiktest.Context;
import com.matthewlemon.dbasiktest.TestSetup;
import com.matthewlemon.dbasiktest.entities.Datamap;
import com.matthewlemon.dbasiktest.exceptions.DuplicateDatamapException;
import com.matthewlemon.dbasiktest.usecases.CreateableDatamapUseCase;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
    public void datamapIsCreated() throws DuplicateDatamapException {
        testDatamap = useCase.createDatamap(TITLE);
        assertThat((testDatamap).getName(), is(TITLE));
    }

    @Test
    public void canGetCountOfLinesInDatamap() throws DuplicateDatamapException {
        testDatamap = useCase.createDatamap(TITLE);
        useCase.addLineToDatamap(TITLE, "Key 1", "Sheet 1", "A2");
        int lineCount = useCase.getLineCountFromDatamap(TITLE);
        assertThat(lineCount, is(1));
    }

    @Test
    public void findDatamapWithName() throws DuplicateDatamapException {
        testDatamap = useCase.createDatamap(TITLE);
        Datamap returnedDatamap = Context.datamapGateway.getDatamap("Test Datamap");
        assertThat(returnedDatamap.getName(), is(TITLE));
    }
}
