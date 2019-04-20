package com.matthewlemon.datamaps.core.usecases.creatingdatamaps;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.matthewlemon.datamaps.core.Context;
import com.matthewlemon.datamaps.core.TestSetup;
import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.exceptions.DatamapNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;
import com.matthewlemon.datamaps.core.parser.DatamapLineType;
import com.matthewlemon.datamaps.core.usecases.CreateableDatamapUseCase;

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
	public void canGetCountOfLinesInDatamap() throws DuplicateDatamapException, DatamapNotFoundException {
		testDatamap = useCase.createDatamap(TITLE);
		useCase.addLineToDatamap(TITLE, "Key 1", "Sheet 1", "A2", DatamapLineType.TEXT);
		int lineCount = useCase.getLineCountFromDatamap(TITLE);
		assertThat(lineCount, is(1));
	}

	@Test
	public void datamapIsCreated() throws DuplicateDatamapException {
		testDatamap = useCase.createDatamap(TITLE);
		assertThat((testDatamap).getName(), is(TITLE));
	}

	@Test
	public void findDatamapWithName() throws DuplicateDatamapException, DatamapNotFoundException {
		testDatamap = useCase.createDatamap(TITLE);
		Datamap returnedDatamap = Context.datamapGateway.getDatamap("Test Datamap");
		assertThat(returnedDatamap.getName(), is(TITLE));
	}
}
