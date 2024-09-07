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
### Maven Shade Plugin Configuration

The provided Maven configuration block is used to package your Java application into a single executable JAR file using the Maven Shade Plugin. Here's a breakdown of the configuration:

#### Plugins Section
```xml
<plugins>
    <!-- Plugin definition -->
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-shade-plugin</artifactId>
        <version>3.2.4</version>
```

- groupId: Specifies the group ID for the Maven Shade Plugin.
- artifactId: Specifies the artifact ID for the Maven Shade Plugin.
- version: The version of the Maven Shade Plugin to use (in this case, version 3.2.4)

```xml
            <executions>
            <execution>
                <phase>package</phase>
                <goals>
                    <goal>shade</goal>
                </goals>
```

- executions: Defines when the plugin should be executed.
- execution: Represents a single execution configuration.
- phase: The Maven build phase during which the plugin should run. Here, it is set to package, meaning the shading process          will occur when Maven packages the project.
- goals: Specifies the goal to be executed. In this case, the shade goal is used to create the shaded JAR.

```xml
                    <configuration>
                    <transformers>
                        <transformer implementation="org.apache.maven.plugins.shade.resource.ManifestResourceTransformer">
                            <mainClass>fr._42.chat.app.Main</mainClass> <!-- Replace with your actual main class -->
                        </transformer>
                    </transformers>
                </configuration>
            </execution>
        </executions>
    </plugin>
</plugins>
```

- configuration: Contains the configuration details for the plugin.
- transformers: Defines the transformers used by the Maven Shade Plugin.
- transformer: Specifies a particular transformer. In this case, it uses the ManifestResourceTransformer to modify the JAR's manifest file.
- implementation: Specifies the implementation class for the transformer. Here, it is set to ManifestResourceTransformer,       which allows you to modify the manifest file in the shaded JAR.
- mainClass: Defines the fully qualified name of the main class to be set in the JAR's manifest file. Replace fr._42.chat.app.Main with the actual class that contains the public static void main(String[] args) method in your application.

# build Element

> The build element in a Maven POM (Project Object Model) file is used to define the build configuration of your project. This includes plugins, resources, directories, and other elements that are essential for the build process.

# plugins Element
> The plugins element is used to define a list of Maven plugins that are required for the build process. Plugins can perform a variety of tasks, such as compiling code, running tests, packaging binaries, generating documentation, and more.

# plugin Element
> The plugin element within the plugins> section defines a specific plugin that Maven will use during the build process. This includes specifying the plugin’s group ID, artifact ID, version, and configuration details.

 - **groupId Element**
> Value: org.apache.maven.plugins
> Meaning: The groupId> specifies the group that the plugin belongs to. This is often a domain name in reverse, as is common in Java packages. In this case, org.apache.maven.plugins indicates that the plugin is part of the official Maven Plugins group maintained by the Apache Maven project.

- **artifactId Element**
> Value: maven-shade-plugin
> Meaning: The artifactId> is the unique identifier for the plugin within the specified group. Here, maven-shade-plugin refers to the Maven Shade Plugin, which is used to create an uber-JAR (a single JAR file containing all dependencies).
- **version Element**
    Value: 3.2.4
> Meaning: The version> specifies the exact version of the plugin to be used. In this case, version 3.2.4 of the Maven Shade Plugin is being used. Specifying the version is important for ensuring consistent builds, as the behavior of plugins may change between versions.

# executions Element
> The executions element is used to specify when and how the plugin should be executed during the Maven build lifecycle. You can define multiple executions with different configurations if needed.
# execution Element
> The execution element defines a specific execution configuration for the plugin. This includes specifying the build phase, goals, and any necessary configuration for that execution.
- **phase Element**
> Value: package
> Meaning: The phase element specifies the build phase in which the plugin’s goals should be executed. Maven’s build lifecycle has predefined phases such as validate, compile, test, package, verify, install, and deploy. The package phase is where the project is packaged into a distributable format, such as a JAR or WAR file. By specifying package, you’re telling Maven to run the plugin during this phase.
- **goals Element**
> The goals element defines the specific goals (tasks) that the plugin should execute during the specified phase.
goal Element

> Value: shade

> Meaning: The goal element specifies the specific task the plugin should perform. In this case, shade is the goal provided by the Maven Shade Plugin. The shade goal creates a shaded (or uber) JAR that includes not only your project’s compiled code but also all of its dependencies. This is particularly useful when you need a standalone executable JAR.
# configuration Element
> The configuration element is where you specify additional configuration options for the plugin’s execution. This can include settings specific to the plugin, such as which files to include/exclude, how to merge resources, and so on.

# transformers Element

 > The transformers element is used to specify resource transformers. Transformers are responsible for modifying the resources that go into the shaded JAR, such as merging properties files, altering manifests, etc.
# transformer Element

> The transformer element defines a specific transformer to be applied. In this context, the transformer modifies the manifest file of the resulting JAR.
implementation Attribute
> Value: org.apache.maven.plugins.shade.resource.ManifestResourceTransformer
> Meaning: The implementation attribute specifies the fully qualified class name of the transformer implementation. The ManifestResourceTransformer is used to modify the manifest file of the JAR. This is useful when you want to add or alter entries in the manifest, such as specifying the Main-Class attribute that tells Java which class contains the main method to execute when running the JAR.
# mainClass Element
 > Value: fr._42.chat.app.Main
> Meaning: The mainClass element within the ManifestResourceTransformer specifies the fully qualified name of the class that contains the public static void main(String[] args) method. This class will be set as the Main-Class entry in the JAR’s manifest file, which tells the JVM which class to execute when running the JAR with the java -jar command. You should replace fr._42.chat.app.Main with the actual main class of your application.

# Summary
> This configuration block is a critical part of your Maven build process if you want to package your application into an executable JAR file that includes all dependencies. By using the Maven Shade Plugin, you ensure that the resulting JAR file is standalone, meaning it can be executed on any machine with a JVM installed, without needing to manually manage dependencies.

> The configuration also specifies which class should be executed as the entry point of your application, making it possible to run your JAR with a simple java -jar command.

# JDBC DIRECT VS HikariCP
> in my main setup for ex00 there will be no main key diff because : 
the connection is opened once at the beginning with 
```java 
    DriverManager.getConnection().
```
It's used to create a ```Statement``` object.
The same Statement (and thus the connection) is reused in all queries (```listUsers```, ```listChatrooms```, and ```listMessages```).
Finally, the connection is closed at the end of the program.
So, the connection is opened once and reused for all the operations before being closed at the end.

# When to Use JDBC Direct vs HikariCP
- **JDBC Direct**: Suitable for small applications with low concurrency and simple database needs.
- **HikariCP**: Ideal for production-level applications, especially those with high concurrency, performance demands, or complex database operations.
# Why Use HikariCP?
In your case, if you're working on a project with potential multiple users, more database operations, and possibly higher concurrency, HikariCP will be beneficial as it provides faster and more efficient database access.

The HikariCP setup would abstract the connection handling behind a pool, so you don’t need to worry about opening and closing connections every time you make a query.

# Current Scenario with Direct JDBC:
- **One Connection**: You're opening the connection once and using it throughout the program for multiple queries (listUsers, listChatrooms, listMessages).
- **No Frequent Open/Close**: Since the connection is reused and only closed at the end, you're not suffering the overhead of opening and closing multiple connections.
- **Single-threaded Execution**: If your program is not handling concurrent requests, the connection is sufficient for this execution flow.
# When HikariCP Makes a Difference:
HikariCP shines when:

**1. Multiple** or Concurrent Connections: If you had a multi-threaded application where each thread or operation needs its own database connection, HikariCP would help manage a pool of connections efficiently, rather than opening a new connection for each thread.

**2. Frequent** Open/Close Cycles: If your code opened and closed database connections frequently (for example, each query or each transaction required a new connection), HikariCP would reduce the overhead by reusing existing connections.

**3. High Load and Scalability*: In a production environment with high load and multiple users accessing the database at the same time, HikariCP would ensure that connections are efficiently managed and reused, preventing bottlenecks or excessive connection creation.



