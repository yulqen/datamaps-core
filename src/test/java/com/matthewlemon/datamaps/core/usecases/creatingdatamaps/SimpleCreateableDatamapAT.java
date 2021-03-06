package com.matthewlemon.datamaps.core.usecases.creatingdatamaps;

import static com.matthewlemon.datamaps.core.parser.DatamapLineType.TEXT;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.exceptions.DatamapLineNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DatamapNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;
import com.matthewlemon.datamaps.core.fixtures.CreateableDatamapFixture;

public class SimpleCreateableDatamapAT {

	@Test
	public void userCanCreateDatamap()
			throws DuplicateDatamapException, DatamapNotFoundException {
		CreateableDatamapFixture fixture = new CreateableDatamapFixture();
		fixture.setUpFixture();
		fixture.userCreatesDatamapWithName("Test Datamap");
		fixture.userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 1", "Sheet 1", "B1", TEXT);
		fixture.userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 2", "Sheet 1", "B2", TEXT);
		assertEquals(2, fixture.canCheckCountOfLinesInDatamap("Test Datamap"));

		List<DatamapLine> dmls = fixture.userCanGetListOfLinesFromDatamap("Test Datamap");
		assertEquals("Test Key 1", dmls.get(0).getKey());
	}
}
