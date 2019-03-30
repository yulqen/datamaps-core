## Todo

### CSVFile object

* Refactor out dependency on desktop to store test CSV file.
* Sort out the `CSVFile` object. At the moment is being mocked in tests.

### Import objects

* Implement `FileImporter` interface and implement `CSVImporter` and
  `XLSX Importer` classes from it.
* In `DatamapGateway` there are methods that deal with the `Datamap`
  (`createDatamap`, `getDatamap`, etc), and methods that deal with
  adding lines to the `Datamap` (`addLineToDatamap`,
  `getDatamapLinesFor`, etc). Split them up.
