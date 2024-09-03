package fr._42.chat.models;
// This code is for establishing connection with MySQL
// database and retrieving data
// from db Java Database connectivity
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void listUsers(Statement st) {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM \"User\"");
            System.err.println("Users:");
            while (rs.next()) {
                System.out.println(rs.getString("login"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
    }
    }

    public static void listChatrooms(Statement st) {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM \"Chatroom\"");
            System.err.println("Chatrooms:");
            while (rs.next()) {
                System.out.println(rs.getString("roomName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void listMessages(Statement st) {
        try {
            ResultSet rs = st.executeQuery("SELECT * FROM \"Message\"");
            System.err.println("Messages:");
            while (rs.next()) {
                System.out.println(rs.getString("text") +  " by " + rs.getString("author") + " in Room : " + rs.getString("room"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] args) throws Exception
	{
        Connection connection = null;
        try {

            // Dotenv dotenv = Dotenv.load();

            // // Access the environment variables
            // String dbPassword = dotenv.get("POSTGRES_PASSWORD");
            // String dbUser = dotenv.get("POSTGRES_USER");
            // String dbName = dotenv.get("POSTGRES_DB");
		String url
			= "jdbc:postgresql://localhost:5432/testdb"; // table details
		String username = "trevor"; // MySQL credentials
		String password = "trevor123@";
		String query
			= "SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'"; // query to be run
        Class.forName("org.postgresql.Driver");// Driver name
		connection = DriverManager.getConnection(url, username, password);
        if (connection != null) {
            System.out.println("Connected to the database!");
        } else {
            System.out.println("Failed to make connection!");
        }
		Statement st = connection.createStatement();
		ResultSet rs
			= st.executeQuery(query); // Execute query
		// rs.next();
        while (rs.next()) {
            String tableName = rs.getString("table_name");
            System.out.println(tableName);
        }
        listUsers(st);
        listChatrooms(st);
        listMessages(st);

        st.close();
		// String name
		// 	= rs.getString("login"); // Retrieve name from db
        // System.out.println(name); // Print result on console
    } catch (SQLException | ClassNotFoundException e) {
        System.out.println("Connection failure.");
        e.printStackTrace();
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
            // st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
		// System.out.println(name); // Print result on console
		 // close statement
		connection.close(); // close connection
		System.out.println("Connection Closed....");
	}
}
