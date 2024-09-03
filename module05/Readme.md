# **JDBC in Java**

Java Database Connectivity (JDBC) is a Java API that allows Java applications to interact with a variety of databases. With JDBC, you can connect to a database, execute SQL queries, and retrieve the results.

## **JDBC API Overview**

The `java.sql` package contains the core classes and interfaces for the JDBC API. Below is a list of the most commonly used interfaces and classes:

### **Popular Interfaces:**

- **Driver interface:** Defines methods that the driver implementation classes must implement to interact with the database.
- **Connection interface:** Represents a connection to the database.
- **Statement interface:** Used to execute static SQL queries.
- **PreparedStatement interface:** Used to execute parameterized SQL queries.
- **CallableStatement interface:** Used to execute stored procedures.
- **ResultSet interface:** Represents the result set of a query.
- **ResultSetMetaData interface:** Provides information about the types and properties of the columns in a `ResultSet`.
- **DatabaseMetaData interface:** Provides information about the database as a whole.
- **RowSet interface:** An interface that extends `ResultSet` and can be used to hold tabular data in-memory.

### **Popular Classes:**

- **DriverManager class:** Manages a list of database drivers. It is used to establish a connection to the database.
- **Blob class:** Represents binary large objects stored as blobs in the database.
- **Clob class:** Represents character large objects stored as clobs in the database.
- **Types class:** Contains constants representing SQL types.

## **Using JDBC to Connect to a Database**

To connect a Java application to a database using JDBC, follow these five essential steps:

### **1. Register the Driver Class**

The first step is to register the database driver with the JDBC DriverManager. This is typically done using the `Class.forName()` method, which dynamically loads the driver class.

```java
Class.forName("oracle.jdbc.driver.OracleDriver");
```

For example, to register the PostgreSQL driver:

```java
Class.forName("org.postgresql.Driver");
```

### **2. Create a Connection**

Once the driver is registered, you can establish a connection to the database using the `DriverManager.getConnection()` method. This method requires a URL that specifies the database location, along with a username and password.

```java
Connection connection = DriverManager.getConnection(
    "jdbc:postgresql://localhost:5432/your_database", 
    "your_username", 
    "your_password"
);
```

### **3. Create a Statement**

To execute SQL queries, you need to create a `Statement` or `PreparedStatement` object using the `Connection` object.

```java
Statement statement = connection.createStatement();
```

Or, if you're using a `PreparedStatement`:

```java
String query = "SELECT * FROM Users WHERE username = ?";
PreparedStatement preparedStatement = connection.prepareStatement(query);
preparedStatement.setString(1, "admin");
```

### **4. Execute Queries**

With the `Statement` or `PreparedStatement` object, you can now execute SQL queries.

For a `SELECT` query:

```java
ResultSet resultSet = statement.executeQuery("SELECT * FROM Users");
```

For an `INSERT`, `UPDATE`, or `DELETE` query:

```java
int rowsAffected = statement.executeUpdate("INSERT INTO Users (username, password) VALUES ('newuser', 'password123')");
```

### **5. Close the Connection**

Finally, it's essential to close the connection to free up database resources. Make sure to close the `ResultSet`, `Statement`, and `Connection` objects when done:

```java
resultSet.close();
statement.close();
connection.close();
```
