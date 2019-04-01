package com.matthewlemon.dbasiktest.usecases.creatingdatamaps;

import com.matthewlemon.dbasiktest.entities.DatamapLine;
import com.matthewlemon.dbasiktest.exceptions.DuplicateDatamapException;
import com.matthewlemon.dbasiktest.fixtures.CreateableDatamapFixture;
import com.matthewlemon.dbasiktest.gateways.DatamapTextType;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SimpleCreateableDatamapAT {

    @Test
    public void userCanCreateDatamap() throws DuplicateDatamapException {
        CreateableDatamapFixture fixture = new CreateableDatamapFixture();
        fixture.setUpFixture();
        fixture.userCreatesDatamapWithName("Test Datamap");
        // this is what we want, but cannot do this just now
//        fixture.userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 1", "Sheet 1", "B1", DatamapType(TEXT));
        fixture.userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 1", "Sheet 1", "B1", new DatamapTextType());
        fixture.userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 2", "Sheet 1", "B2", new DatamapTextType());
        assertEquals(fixture.canCheckCountOfLinesInDatamap("Test Datamap"), 2);

        List<DatamapLine> dmls = fixture.userCanGetListOfLinesFromDatamap("Test Datamap");
        assertEquals(dmls.get(0).getKey(), "Test Key 1");
    }
}
