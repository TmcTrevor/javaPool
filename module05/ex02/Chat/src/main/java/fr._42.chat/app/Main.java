package fr._42.chat.app;
// This code is for establishing connection with MySQL
// database and retrieving data
// from db Java Database connectivity
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Scanner;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import fr._42.chat.models.Chatroom;
import fr._42.chat.models.Message;
import fr._42.chat.models.User;
import fr._42.chat.repositories.MessageRepositoryJdbcImpl;

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
            System.err.println(e.getMessage());
        }
    }
	public static void main(String[] args) throws Exception
	{
        Connection connection = null;
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/testdb");
            config.setUsername("trevor");
            config.setPassword("trevor123@");
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            HikariDataSource ds = new HikariDataSource(config);
            MessageRepositoryJdbcImpl messageRepositoryJdbcImpl = new MessageRepositoryJdbcImpl(ds);
            User testUser = new User(6, "test", "test");
            Chatroom testRoom = new Chatroom(1, testUser, "random");
            Message testmessage = new Message(testUser, testRoom, "SOme random text message1");
            System.out.println("before : id = " + testmessage.getId());
            messageRepositoryJdbcImpl.save(testmessage);
            System.out.println("after : id = " + testmessage.getId());


        } catch (RuntimeException e)
        {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Connection failure.");
//        e.printStackTrace();
        } finally {
        try {
            if (connection != null) {
                connection.close();
            }
            // st.close();
        } catch (SQLException ex) {
            System.err.println("Error :" + ex.getMessage() );
//            ex.printStackTrace();

        }
    }
		// System.out.println(name); // Print result on console
		 // close statement
//		connection.close(); // close connection
		System.out.println("Connection Closed....");
	}
}
