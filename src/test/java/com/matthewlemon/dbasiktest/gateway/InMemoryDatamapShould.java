package com.matthewlemon.dbasiktest.gateway;

import com.matthewlemon.dbasiktest.TestSetup;
import com.matthewlemon.dbasiktest.doubles.InMemoryDatamapGateway;
import com.matthewlemon.dbasiktest.entities.CSVFile;
import com.matthewlemon.dbasiktest.entities.Datamap;
import com.matthewlemon.dbasiktest.entities.DatamapLine;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class InMemoryDatamapShould {

    @Mock
    private CSVFile csvFile;

    @Test
    public void setUp() {
        TestSetup.setupContext();
        // TODO we need to Mock this object
        //  we also need to find out about tmp files again
        csvFile = new CSVFile("test.csv");
    }

    @Test
    public void testListGetter() {
        List<String> list = new ArrayList();
        list.add("tits");
        assertEquals(list.get(0), "tits");
    }

    @Test
    public void addLineToDatamap() {
        InMemoryDatamapGateway gateway = new InMemoryDatamapGateway();
        gateway.createDatamap("Test Datamap");
        gateway.addLineToDatamap("Test Datamap",
                "Test Key", "Sheet 1", "B1");
        gateway.getDatamap("Test Datamap");
        List<DatamapLine> datamaplines = gateway.getDataLinesFor("Test Datamap");
        assertEquals(datamaplines.get(0).getKey(), "Test Key");
    }

    @Test
    public void useGatewayToAddToDatamapWithCSV() {
        List<String> data = new ArrayList<>();
        data.add("Test Key 1");
        data.add("Test Sheet 1");
        data.add("Test CellRef 1");

        File testFile = new File("/home/lemon/Desktop/test.csv");

        DatamapLine dml = new DatamapLine("Test Key 1", "Test Sheet 1",
                "Test CellRef 1");

        when(csvFile.getFile()).thenReturn(testFile);

        InMemoryDatamapGateway gateway = new InMemoryDatamapGateway();

        Datamap datamap = gateway.createDatamap("Test Datamap");
        gateway.addDataToDatamapWithCSV("Test Datamap", csvFile);
        assertEquals(gateway.getDatamap("Test Datamap")
                .getDatamapLines().get(0).getKey(), dml.getKey());
    }

    @Test
    public void datamapCanReadCSV() throws IOException {
        File testFile = new File("/home/lemon/Desktop/test.csv");
        Datamap datamap = new Datamap("Test Datamap");
        datamap.readCSV(testFile);
        assertEquals(datamap.getDatamapLines().get(0).getKey(), "Test Key 1");
        assertEquals(datamap.getDatamapLines().get(1).getKey(), "Test Key 2");
    }

}
