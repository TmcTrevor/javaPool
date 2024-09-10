package fr._42.chat.repositories;

import fr._42.chat.exceptions.ChatroomNotFoundException;
import fr._42.chat.exceptions.UserNotFoundException;
import fr._42.chat.models.Chatroom;

import javax.sql.DataSource;
import fr._42.chat.models.Chatroom;
import fr._42.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {
    private final DataSource dataSource;
    public UserRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> getUserById(int id)
    {
        String query = "SELECT * FROM \"User\" WHERE id = ?";
        try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(query)){
            pst.setInt(1, id);
            ResultSet st = pst.executeQuery();
            if (!st.next())
                throw new UserNotFoundException("User with id : " + id+ " Not Found");
//            st.next();
            User user = new User(st.getInt("id"), st.getString("login"), st.getString("password"));
            return Optional.of(user);

        } catch (SQLException e) {
            System.err.println("test = "+ e.getMessage());
            throw new UserNotFoundException(e.getMessage());

        }
    }

    @Override
    public List<User> findAll(int page, int size)
    {
        ArrayList<User> list = new ArrayList<>(size);

        try {
            String query = """
                    SELECT u.id AS "userID", u.* , r.id AS "roomID", r.*, m.id AS "messageID"
                    FROM "USER" u
                    JOIN  
                    """;
            Connection con = dataSource.getConnection();
            PreparedStatement st = con.prepareStatement(query);
        } catch (Exception e)
        {
            System.err.println("Error : " +e.getMessage());
        }
        return list;
    }

}