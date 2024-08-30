SELECT
SELECT TOP 3 * FROM Customers; (LIMIT number; for mysql)
SELECT DISTINCT;

SELECT MAX(column)/MIN(column)
SELECT COUNT(*) / COUNT(column) /  COUNT(DISTINCT column) / SELECT COUNT(*) AS [Number of records]
SELECT OrderID, SUM(Quantity) AS [Total Quantity]
FROM OrderDetails
GROUP BY OrderID;




UPDATE

> 	```psql
	UPDATE table
	SET (data to update) colmns1 = "...", colmns2 = ".."
	WHERE ...```



INSERT
	INSERT INTO table (columns ..) (values) or INSERT INTO table (values)

CREATE DATABASE
> CREATE DATABASE db_name;

Drop DATABASE
> DROP DATABASE db_name;

BACKUP DATABASE
>	BACKUP DATABASE db_name
	TO DISK = 'D:\backups\testDB.bak';

CREATE TABLE
>	CREATE TABLE Persons (
    PersonID int,
    LastName varchar(255),
    FirstName varchar(255),
    Address varchar(255),
    City varchar(255)
);

**Nb Constraints**
	CREATE TABLE table_name (
    column1 datatype constraint,
    column2 datatype constraint,
    column3 datatype constraint,
    ....
);

**NOT NULL** - Ensures that a column cannot have a NULL value
**UNIQUE** - Ensures that all values in a column are different
**PRIMARY KEY** - A combination of a NOT NULL and UNIQUE. Uniquely identifies each row in a table
**FOREIGN KEY** - Prevents actions that would destroy links between tables
**CHECK** - Ensures that the values in a column satisfies a specific condition
**DEFAULT** - Sets a default value for a column if no value is specified
**CREATE INDEX** - Used to create and retrieve data from the database very quickly


> **PRIMARY KEY**
> ```Mysql
		 CREATE TABLE Persons (
    ID int NOT NULL AUTO_INCREMENT UNIQUE,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    Age int,
    PRIMARY KEY (ID)
);```

> **FOREIGN KEY**
>	CREATE TABLE Orders (
    	OrderID int NOT NULL,
    	OrderNumber int NOT NULL,
    	PersonID int,
    	PRIMARY KEY (OrderID),
    	FOREIGN KEY (PersonID) REFERENCES Persons(PersonID)
	);

> **CHECK**
>	CREATE TABLE Persons (
    	ID int NOT NULL,
    	LastName varchar(255) NOT NULL,
    	FirstName varchar(255),
    	Age int,
    	CHECK (Age>=18)
);


ALTER TABLE
> 	ALTER TABLE Customers
	ADD Email varchar(255);

DROP TABLE
> DROP TABLE table_name;

CREATE INDEX
DROP INDEX





DELETE FROM table where ....;
DELETE FROM table; -> clear table;



