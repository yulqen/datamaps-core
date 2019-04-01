package com.matthewlemon.dbasiktest.fixtures;

import com.matthewlemon.dbasiktest.TestSetup;
import com.matthewlemon.dbasiktest.entities.CSVFile;
import com.matthewlemon.dbasiktest.entities.Datamap;
import com.matthewlemon.dbasiktest.entities.DatamapLine;
import com.matthewlemon.dbasiktest.exceptions.DuplicateDatamapException;
import com.matthewlemon.dbasiktest.gateways.DatamapType;
import com.matthewlemon.dbasiktest.usecases.CreateableDatamapUseCase;

import java.util.List;

public class CreateableDatamapFixture {

    private CreateableDatamapUseCase useCase;

    public void setUpFixture() {
        this.useCase = new CreateableDatamapUseCase();
        TestSetup.setupContext();
    }

    public void userCreatesDatamapWithName(String datamapName) throws DuplicateDatamapException {
        useCase.createDatamap(datamapName);
    }

    public void userAddsSingleLineOfDataToDatamap(String datamapName, String key, String sheetName, String cellRef, DatamapType type) {
        this.useCase.addLineToDatamap(datamapName, key, sheetName, cellRef, type);
    }

    public List<DatamapLine> userCanGetListOfLinesFromDatamap(String datamapName) {
        Datamap datamap = useCase.getDatamap(datamapName);
        return datamap.getDatamapLines();
    }

    public int canCheckCountOfLinesInDatamap(String datamapName) {
        return this.useCase.getLineCountFromDatamap(datamapName);
    }

    public void userAddsDataToDatamapUsingCSV(String datamapName, CSVFile csvFile) {
        useCase.addCSVDataToDatamap(datamapName, csvFile);
    }
}
