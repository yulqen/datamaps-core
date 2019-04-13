package com.matthewlemon.datamaps.core.fixtures;

import com.matthewlemon.datamaps.core.TestSetup;
import com.matthewlemon.datamaps.core.entities.CSVFile;
import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;
import com.matthewlemon.datamaps.core.parser.DatamapType;
import com.matthewlemon.datamaps.core.usecases.CreateableDatamapUseCase;

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
