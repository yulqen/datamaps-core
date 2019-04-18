### Todo

* Implemenet a Return superclass and make InMemoryReturn implement it.
* Check we are still basically Clean in terms of architecture.
* Can we obtain integers from the parser, or must it be doubles?
* Get rid of com.matthewlemon.datamaps.core.parser.DatamapType.
* Refactor InMemoryDatamapShould test.
* Move functionality to excel parser usecase.

### Long Term

* Implement a FileSystemReturn, and a Return interface

### Done

* Exceptions raised when parser cannot find data on a sheet/cell combination.
* Ability to be able to get datamapline from datamp by querying the key name, rather than the index.
* Implement name field in InMemoryReturn class.
* Refactor ReturnParser so that you pass the return into the constructor.