## Todo

### New Acceptance Test

* AT for the whole trip
* Needs to include Return and Template objects, and implement the DatamapType system.

### Datamap

* Implement typing system within Datamap (both in text in the CSV importer and via the API).
* We need to implement the concept of an id, or ensure that the name is unique. (Ensured that adding a Datamap with an existing name raises an exception **DONE!**)

### Import objects

* Implement `FileImporter` interface and implement `CSVImporter` and
  `XLSX Importer` classes from it.
* In `DatamapGateway` there are methods that deal with the `Datamap`
  (`createDatamap`, `getDatamap`, etc), and methods that deal with
  adding lines to the `Datamap` (`addLineToDatamap`,
  `getDatamapLinesFor`, etc). Split them up.

### CSVFile object

* Refactor out dependency on desktop to store test CSV file. **DONE!**
* Sort out the `CSVFile` object. At the moment is being mocked in tests. **DONE!**