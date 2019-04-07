## Todo

* Type-checking system

* Implement `FileImporter` interface and implement `CSVImporter` and
  `XLSX Importer` classes from it. These are currently started in the guise of `ImportFileGateway`.
* In `DatamapGateway` there are methods that deal with the `Datamap`
  (`createDatamap`, `getDatamap`, etc), and methods that deal with
  adding lines to the `Datamap` (`addLineToDatamap`,
  `getDatamapLinesFor`, etc). Split them up.