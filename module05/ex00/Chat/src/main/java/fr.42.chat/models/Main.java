// This code is for establishing connection with MySQL
// database and retrieving data
// from db Java Database connectivity

/*
*1. import --->java.sql
*2. load and register the driver ---> com.jdbc.
*3. create connection
*4. create a statement
*5. execute the query
*6. process the results
*7. close
*/

import java.io.*;
import java.sql.*;

class GFG {
	public static void main(String[] args) throws Exception
	{
        Connection connection = null;
        try {
		String url
			= "jdbc:postgresql://localhost:5432/postgres"; // table details
		String username = "trevor"; // MySQL credentials
		String password = "trevor123@";
		String query
			= "select *from users"; // query to be run
        Class.forName("org.postgresql.Driver");/ Driver name
		connection = DriverManager.getConnection(url, user, password);
        if (connection != null) {
            System.out.println("Connected to the database!");
        } else {
            System.out.println("Failed to make connection!");
        }
		// Statement st = con.createStatement();
		// ResultSet rs
		// 	= st.executeQuery(query); // Execute query
		// rs.next();
		// String name
		// 	= rs.getString("name"); // Retrieve name from db
    } catch (SQLException | ClassNotFoundException e) {
        System.out.println("Connection failure.");
        e.printStackTrace();
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
		// System.out.println(name); // Print result on console
		st.close(); // close statement
		con.close(); // close connection
		System.out.println("Connection Closed....");
	}
}
