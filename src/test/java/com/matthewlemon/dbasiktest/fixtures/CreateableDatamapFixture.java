package com.matthewlemon.dbasiktest.fixtures;

import com.matthewlemon.dbasiktest.entities.Datamap;
import com.matthewlemon.dbasiktest.entities.DatamapLine;
import com.matthewlemon.dbasiktest.usecases.CreateableDatamapUseCase;

import java.util.List;

public class CreateableDatamapFixture {

    private CreateableDatamapUseCase useCase;

    public void setUpFixture() {
        this.useCase = new CreateableDatamapUseCase();
    }

    public void userCreatesDatamapWithName(String datamapName) {
        CreateableDatamapUseCase useCase = new CreateableDatamapUseCase();
    }

    public void userAddsSingleLineOfDataToDatamap(String datamapName, String key, String sheetName, String cellRef) {
        this.useCase.addLineToDatamap(datamapName, key, sheetName, cellRef);
    }

    public List<DatamapLine> userCanGetListOfLinesFromDatamap(String datamapName) {
        return null;
    }

    public int canCheckCountOfLinesInDatamap(String datamapName) {
        return 0;
    }
}
