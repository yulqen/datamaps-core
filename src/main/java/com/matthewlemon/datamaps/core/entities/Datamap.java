package com.matthewlemon.datamaps.core.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Datamap {

    private String datamapName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private final String COMMA_DELIMITER = ",";

    protected Datamap() {}

    public Datamap(String datamapName) {
        this.datamapName = datamapName;
    }

    List<DatamapLine> datamapLines = new ArrayList<>();

    public String getName() {
        return datamapName;
    }

    public List<DatamapLine> getDatamapLines() {
        return datamapLines;
    }

    public void setName(String datamapName) {
        this.datamapName = datamapName;
    }

    public void addDatamapLine(DatamapLine datamapLine) {
        datamapLines.add(datamapLine);
    }

    public void readCSV(File testFile) {
        BufferedReader br;
        try {
            String line;
            br = new BufferedReader(new FileReader(testFile));
            while ((line = br.readLine()) != null) {
                String[] parsedLine = line.split(COMMA_DELIMITER);
                DatamapLine dml = new DatamapLine(
                        parsedLine[0], parsedLine[1], parsedLine[2]);
                this.addDatamapLine(dml);
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }

    public void deleteAllLines() {
        datamapLines.clear();
    }

	public String getDataKeyForLine(int index) {
		return datamapLines.get(index).getKey();
	}
}