package com.matthewlemon.datamaps.core.repositories;

import com.matthewlemon.datamaps.core.entities.CSVFile;
import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.entities.DatamapLine;
import com.matthewlemon.datamaps.core.exceptions.DatamapLineNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DatamapNotFoundException;
import com.matthewlemon.datamaps.core.exceptions.DuplicateDatamapException;
import com.matthewlemon.datamaps.core.parser.DatamapLineType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PersistentDatamapRepository implements DatamapRepository {
    @Override
    public void addDataToDatamapWithCSV(String datamapName, CSVFile csvFile) throws DatamapNotFoundException {

    }

    @Override
    public void addLineToDatamap(String datamapName, String key, String sheetName, String cellRef, DatamapLineType text) throws DatamapNotFoundException {

    }

    @Override
    public Datamap createDatamap(String datamapName) throws DuplicateDatamapException {
        return null;
    }

    @Override
    public int datamapCount() {
        return 0;
    }

    @Override
    public boolean datamapExists(String datamapName) {
        return false;
    }

    @Override
    public void deleteAllDatamaps() throws Exception {

    }

    @Override
    public void deleteAllLinesIn(String test_datamap) {

    }

    @Override
    public List<DatamapLine> getDataLinesFor(String datamapName) throws DatamapNotFoundException {
        return null;
    }

    @Override
    public Datamap getDatamap(String test_datamap) throws DatamapNotFoundException {
        return null;
    }

    @Override
    public DatamapLine getDatamapLineFrom(String datamapName, String key) throws DatamapLineNotFoundException, DatamapNotFoundException {
        return null;
    }

    @Override
    public Object save(Object entity) {
        return null;
    }

    @Override
    public Iterable saveAll(Iterable entities) {
        return null;
    }

    @Override
    public Optional findById(Object o) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Object o) {
        return false;
    }

    @Override
    public List<Datamap> findAll() {
        return null;
    }

    @Override
    public Iterable findAllById(Iterable iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Object o) {

    }

    @Override
    public void delete(Object entity) {

    }

    @Override
    public void deleteAll(Iterable entities) {

    }

    @Override
    public void deleteAll() {

    }
}
