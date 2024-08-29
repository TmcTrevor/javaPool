> JDBC stands for Java Database Connectivity. JDBC is a Java API to connect and execute the query with the database.

**The java.sql package contains classes and interfaces for JDBC API. A list of popular interfaces of JDBC API are given below:**

- Driver interface
- Connection interface
- Statement interface
- PreparedStatement interface
- CallableStatement interface
- ResultSet interface
- ResultSetMetaData interface
- DatabaseMetaData interface
- RowSet interface
> A list of popular classes of JDBC API are given below:

- DriverManager class
- Blob class
- Clob class
- Types class

> We can use JDBC API to handle database using Java program and can perform the following activities:

1. Connect to the database
2. Execute queries and update statements to the database
3. Retrieve the result received from the database.


# ** Connect to DB In java **
> There are 5 steps to connect any java application with the database using JDBC. These steps are as follows:

- Register the Driver class
- Create connection
- Create statement
- Execute queries
- Close connection


**: 1) Register the driver class**
> The forName() method of Class class is used to register the driver class. This method is used to dynamically load the driver class.

```java
	public static void forName(String className)throws ClassNotFoundException
	Class.forName("oracle.jdbc.driver.OracleDriver");
```


