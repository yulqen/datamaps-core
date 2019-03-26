package com.matthewlemon.dbasiktest.fixtures;

import com.matthewlemon.dbasiktest.entities.Datamap;
import com.matthewlemon.dbasiktest.entities.DatamapLine;
import com.matthewlemon.dbasiktest.usecases.CreateableDatamapUseCase;

import java.util.List;

public class CreateableDatamapFixture {

    CreateableDatamapUseCase useCase = new CreateableDatamapUseCase();

    public static Datamap userCreatesDatamapWithName(String test_datamap) {
        return null;
    }

    public static void userAddsSingleLineOfDataToDatamap(String datamapName, String key, String sheetName, String cellRef) {
    }

    public static List<DatamapLine> userCanGetListOfLinesFromDatamap(String datamapName) {
        return null;
    }

    public static int canCheckCountOfLinesInDatamap(String datamapName) {
        return 0;
    }
}
