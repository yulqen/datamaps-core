package com.matthewlemon.dbasiktest.usecases;

import com.matthewlemon.dbasiktest.fixtures.CreateableDatamapFixture;
import org.junit.Test;

public class CreateAndAddDatamapWithCSVAT {

    @Test
    public void userCanCreateDatamapUsingCSVForDatamapLines() {
        CreateableDatamapFixture fixture = new CreateableDatamapFixture();
        fixture.setUpFixture();
        fixture.userCreatesDatamapWithName("Test Datamap");
//        fixture.userAddsDataToDatamapUsingCSV("Test Datamap");
    }
}
