## Todo

### Datamap

* Implement typing system within Datamap (both in text in the CSV importer and via the API).

### Import objects

* Implement `FileImporter` interface and implement `CSVImporter` and
  `XLSX Importer` classes from it.
* In `DatamapGateway` there are methods that deal with the `Datamap`
  (`createDatamap`, `getDatamap`, etc), and methods that deal with
  adding lines to the `Datamap` (`addLineToDatamap`,
  `getDatamapLinesFor`, etc). Split them up.