package fr._42.chat.app;
// This code is for establishing connection with MySQL
// database and retrieving data
// from db Java Database connectivity
import java.sql.Connection;
//import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.util.Optional;
//import java.util.Scanner;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
//import fr._42.chat.models.Chatroom;
import fr._42.chat.models.Message;
//import fr._42.chat.models.User;
import fr._42.chat.models.User;
import fr._42.chat.repositories.MessageRepositoryJdbcImpl;

public class Main {
	public static void main(String[] args)
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

            Optional<Message> message = messageRepositoryJdbcImpl.findById(6L);
            if (message.isPresent())
            {
                System.out.println("OLD " + message.get());
                message.get().setText("some Random text updated with new User 3");
                message.get().setDatetime(null);
                User testUser = new User(3, "test", "rrr4r5");
                message.get().setAuthor(testUser);
                messageRepositoryJdbcImpl.update(message.get());
               message = messageRepositoryJdbcImpl.findById(6L);
                message.ifPresent(value -> System.out.println("New " + value));
            }


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
