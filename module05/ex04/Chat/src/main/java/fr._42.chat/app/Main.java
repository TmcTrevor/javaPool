package fr._42.chat.app;
// This code is for establishing connection with MySQL
// database and retrieving data
// from db Java Database connectivity
import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.util.List;
//import java.util.Optional;
//import java.util.Scanner;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
//import fr._42.chat.models.Chatroom;
import fr._42.chat.models.Chatroom;
//import fr._42.chat.models.Message;
//import fr._42.chat.models.User;
import fr._42.chat.models.User;
//import fr._42.chat.repositories.MessageRepositoryJdbcImpl;
import fr._42.chat.repositories.UserRepositoryJdbcImpl;

public class Main {
	public static void main(String[] args)
    {
        try {
            UserRepositoryJdbcImpl userRepositoryJdbcImpl = getUserRepositoryJdbc();
            List<User> users = userRepositoryJdbcImpl.findAll(0,2);
            for (User  user : users)
            {
                System.out.println("User with Id = " + user.getId());
                System.out.println("{");
                System.out.println("\tid = "+ user.getId() + ",");
                System.out.println("\tlogin = "+ user.getLogin()+ ",");
                System.out.println("\tpassword = "+ user.getPassword()+ ",");
                System.out.println("\tcreatedRooms = {");
                for (Chatroom room : user.getCreatedRooms())
                {
                    System.out.println("\t\tRoom = {");
                    System.out.println("\t\t\tid : " + room.getId() + ",");
                    System.out.println("\t\t\tname : " + room.getRoomName());
                    System.out.println("\t\t\tCreator : { ");
                    System.out.println("\t\t\t\tid : "+ room.getOwner().getId());
                    System.out.println("\t\t\t\tlogin : "+ room.getOwner().getLogin());
                    System.out.println("\t\t\t} :");
                    System.out.println("\t\t}");
                }
                System.out.println("\t}");
                System.out.println("\tSocializedRooms = {");
                for (Chatroom room : user.getSocializingRooms())
                {
                    System.out.println("\t\tRoom = {");
                    System.out.println("\t\t\t" + room.getId() + ",");
                    System.out.println("\t\t\t" + room.getRoomName());
                    System.out.println("\t\t\tCreator : { ");
                    System.out.println("\t\t\t\tid : "+ room.getOwner().getId());
                    System.out.println("\t\t\t\tlogin : "+ room.getOwner().getLogin());
                    System.out.println("\t\t\t}");
                    System.out.println("\t\t}");

                }
                System.out.println("\t}");
                System.out.println("}");
//                System.out.println("\t}");

//                System.out.println(",\tpassword = "+ user.getId());
//                System.out.println(user);
                System.out.println("----------------------------");
            }
        } catch (RuntimeException e)
        {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Connection failure.");
        }
		System.out.println("Connection Closed....");
	}

    private static UserRepositoryJdbcImpl getUserRepositoryJdbc() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/testdb");
        config.setUsername("trevor");
        config.setPassword("trevor123@");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);
        return new UserRepositoryJdbcImpl(ds);
    }
}
