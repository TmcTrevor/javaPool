package fr._42.services;

import fr._42.exceptions.AlreadyAuthenticatedException;
import fr._42.exceptions.EntityNotFoundException;
import fr._42.models.User;
import fr._42.repositories.UsersRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersServiceImpl {

//    private DataSource dataSource;
    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
//        this.dataSource = dataSource;
        this.usersRepository = usersRepository;
    }

//    public User findByLogin(String login)
//    {
//        try {
//            Connection connection = dataSource.getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
//            preparedStatement.setString(1, login);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return new User(resultSet.getInt("id"), resultSet.getString("Login"), resultSet.getString("Password"), resultSet.getBoolean("isAuthenticated"));
//            }
//            else
//                throw new EntityNotFoundException("User with login " + login + " not found");
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void update(User user)
//    {
//        try {
//            Connection connection = dataSource.getConnection();
//            PreparedStatement ps = connection.prepareStatement("UPDATE \"User\" SET Login = ? , Password = ? , isAuthenticated = ? WHERE id  = ?");
//            ps.setString(1, user.getLogin());
//            ps.setString(2, user.getPassword());
//            ps.setBoolean(3, user.isAuthenticated());
//            ps.setInt(4, user.getIdentifier());
//           int rowsAffected =  ps.executeUpdate();
//            if (rowsAffected <= 0) {
//                throw new EntityNotFoundException("user with login " + user.getLogin() + " not found");
//            }
//
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    boolean authenticate(String login, String password)
    {

        try {
            User user = usersRepository.findByLogin(login);
            if (user.isAuthenticated())
                throw new AlreadyAuthenticatedException("User with login " + login + " already authenticated");
            if (password.equals(user.getPassword()))
            {
                user.setAuthenticated(true);
                usersRepository.update(user);
                return true;
            }
            return false;
        } catch (EntityNotFoundException e) {
//            System.err.println("Error " + e.getMessage());
            throw new EntityNotFoundException("User with login " + login + " not found");
        }

    }
}
