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

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class InMemoryDatamapShould {

    @Mock
    private CSVFile csvFile;
    private final String COMMA_DELIMITER = ",";

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
    @Ignore("Cannot run until we have CSVFile.getData() running.")
    public void addDataToDatamapWithCSV() {
        List<String> data = new ArrayList<>();
        data.add("Test Key");
        data.add("Test Sheet");
        data.add("Test CellRef");

        DatamapLine dml = new DatamapLine("Test Key", "Test Sheet",
                "Test CellRef");

        when(csvFile.getData()).thenReturn(data);

        InMemoryDatamapGateway gateway = new InMemoryDatamapGateway();

        Datamap datamap = gateway.createDatamap("Test Datamap");
        gateway.addDataToDatamapWithCSV("Test Datamap", csvFile);
        gateway.getDataLinesFor("Test Datamap");
        assertEquals(datamap.getDatamapLines().get(0).getKey(), dml.getKey());
    }

    @Test
    public void yieldDataFromCSVFile() throws IOException {
        File testFile = new File("/home/lemon/Desktop/test.csv");
        BufferedReader br = null;
        Scanner scanner = null;
        try {
            scanner = new Scanner(testFile);
            scanner.useDelimiter(COMMA_DELIMITER);
            while (scanner.hasNext()) {
                System.out.println(scanner.next() + "   ");
            }
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } finally {
            scanner.close();
        }

    }
}
