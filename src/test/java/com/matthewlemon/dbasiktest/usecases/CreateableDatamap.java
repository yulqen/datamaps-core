package com.matthewlemon.dbasiktest.usecases;

import com.matthewlemon.dbasiktest.entities.DatamapLine;
import com.matthewlemon.dbasiktest.fixtures.CreateableDatamapFixture;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CreateableDatamap {

    @Test
    public void userCanCreateDatamap() {
        CreateableDatamapFixture fixture = new CreateableDatamapFixture();
        fixture.setUpFixture();
        fixture.userCreatesDatamapWithName("Test Datamap");
        fixture.userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 1", "Sheet 1", "B1");
        fixture.userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 2", "Sheet 1", "B2");
        assertEquals(fixture.canCheckCountOfLinesInDatamap("Test Datamap"), 2);

        List<DatamapLine> dmls = fixture.userCanGetListOfLinesFromDatamap("Test Datamap");
        assertEquals(dmls.get(0).getKey(), "Test Key 1");
    }
}
