package com.matthewlemon.datamaps.core.usecases.creatingdatamaps;

import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;
import com.matthewlemon.datamaps.core.fixtures.CreateableDatamapFixture;
import com.matthewlemon.datamaps.core.parser.DatamapType;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimpleCreateableDatamapAT {

	@Test
    public void userCanCreateDatamap() throws DuplicateDatamapException {
        CreateableDatamapFixture fixture = new CreateableDatamapFixture();
        fixture.setUpFixture();
        fixture.userCreatesDatamapWithName("Test Datamap");
        fixture.userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 1", "Sheet 1", "B1", new DatamapType());
        fixture.userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 2", "Sheet 1", "B2", new DatamapType());
        assertEquals(2, fixture.canCheckCountOfLinesInDatamap("Test Datamap"));

        List<DatamapLine> dmls = fixture.userCanGetListOfLinesFromDatamap("Test Datamap");
        assertEquals("Test Key 1", dmls.get(0).getKey());
    }
}
