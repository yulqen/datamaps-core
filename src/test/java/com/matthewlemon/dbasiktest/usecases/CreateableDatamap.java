package com.matthewlemon.dbasiktest.usecases;

import com.matthewlemon.dbasiktest.entities.Datamap;
import com.matthewlemon.dbasiktest.entities.DatamapLine;
import org.junit.Test;

import java.util.List;

import static com.matthewlemon.dbasiktest.fixtures.CreateableDatamapFixture.*;
import static org.junit.Assert.assertEquals;

public class CreateableDatamap {

    @Test
    public void userCanCreateDatamap() {
        Datamap datamap = userCreatesDatamapWithName("Test Datamap");
        userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 1", "Sheet 1", "B1");
        userAddsSingleLineOfDataToDatamap("Test Datamap", "Test Key 2", "Sheet 1", "B2");
        assertEquals(canCheckCountOfLinesInDatamap("Test Datamap"), 2);

        List<DatamapLine> dmls = userCanGetListOfLinesFromDatamap("Test Datamap");
        assertEquals(dmls.get(0).getKey(), "Test Key 1");
    }
}
