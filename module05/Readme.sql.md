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
CREATE TABLE
ALTER TABLE
DROP TABLE
CREATE INDEX
DROP INDEX





DELETE FROM table where ....;
DELETE FROM table; -> clear table;



