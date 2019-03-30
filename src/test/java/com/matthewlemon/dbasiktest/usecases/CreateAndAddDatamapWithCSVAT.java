package com.matthewlemon.dbasiktest.usecases;

import com.matthewlemon.dbasiktest.entities.CSVFile;
import com.matthewlemon.dbasiktest.fixtures.CreateableDatamapFixture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CreateAndAddDatamapWithCSVAT {

    private static File testFile;

    @Mock
    private CSVFile csvFile;

    @Before
    public void setUp() {
        testFile = new File("/home/lemon/Desktop/test.csv");
    }

    @Test
    public void userCanCreateDatamapUsingCSVForDatamapLines() {
        when(csvFile.getFile()).thenReturn(testFile);
        CreateableDatamapFixture fixture = new CreateableDatamapFixture();
        fixture.setUpFixture();
        fixture.userCreatesDatamapWithName("Test Datamap");
        fixture.userAddsDataToDatamapUsingCSV("Test Datamap", csvFile);
    }
}
