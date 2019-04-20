### Todo

* Do not halt when exception on datamapline value type is hit - store in a report!
* Can we obtain integers from the parser, or must it be doubles?
* Implement the Date DatamapLineType enum.
* Move functionality to excel parser usecase.
* Implement rules alongside DatamapLineType.

### Long Term

* Check we are still basically Clean in terms of architecture.
* Implement a Return superclass and make InMemoryReturn implement it.
* Implement a FileSystemReturn, and a Return interface

### Done

* Make DatamapValue class a value object.
* Use enum when constructing DatamapLines, or write a wrapper method that allows for it to me omitted - what are the consequences?
	* DECISION: type must currently be included for each DatamapLine object
* Get rid of com.matthewlemon.datamaps.core.parser.DatamapType.
* Check types returned from a parsed spreadsheet match what is in Datamap
* Exceptions raised when parser cannot find data on a sheet/cell combination.
* Ability to be able to get datamapline from datamp by querying the key name, rather than the index.
* Implement name field in InMemoryReturn class.
* Refactor ReturnParser so that you pass the return into the constructor.