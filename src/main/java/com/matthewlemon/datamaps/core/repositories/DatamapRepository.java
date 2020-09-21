package com.matthewlemon.datamaps.core.repositories;

import java.util.List;

import com.matthewlemon.datamaps.core.entities.CSVFile;
import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.exceptions.DatamapLineNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DatamapNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;
import com.matthewlemon.datamaps.core.parser.DatamapLineType;
import org.springframework.data.repository.CrudRepository;

public interface DatamapRepository extends CrudRepository<Datamap, Integer> {

    void addDataToDatamapWithCSV(String datamapName, CSVFile csvFile) throws DatamapNotFoundException;

    void addLineToDatamap(String datamapName, String key, String sheetName, String cellRef, DatamapLineType text) throws DatamapNotFoundException;

    Datamap createDatamap(String datamapName) throws DuplicateDatamapException;

    int datamapCount();

    boolean datamapExists(String datamapName);

    void deleteAllDatamaps() throws Exception;

    void deleteAllLinesIn(String test_datamap);

	List<DatamapLine> getDataLinesFor(String datamapName) throws DatamapNotFoundException;

	Datamap getDatamap(String test_datamap) throws DatamapNotFoundException;

	DatamapLine getDatamapLineFrom(String datamapName, String key) throws DatamapLineNotFoundException, DatamapNotFoundException;

}
