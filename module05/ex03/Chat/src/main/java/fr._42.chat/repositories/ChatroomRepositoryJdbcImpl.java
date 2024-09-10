package fr._42.chat.repositories;

import fr._42.chat.exceptions.ChatroomNotFoundException;
import fr._42.chat.models.Chatroom;

import javax.sql.DataSource;
import fr._42.chat.models.Chatroom;
import fr._42.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ChatroomRepositoryJdbcImpl implements ChatroomRepository {
    private final DataSource dataSource;
    public ChatroomRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Chatroom> getRoomById(int id)
    {
        String query = "SELECT * FROM \"Chatroom\" WHERE id = ?";
        try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(query)){
            pst.setInt(1, id);
            ResultSet st = pst.executeQuery();
            if (!st.next())
                throw new ChatroomNotFoundException("Room with id : "+ id + " Not Found!");
//            st.next();
            UserRepositoryJdbcImpl userRep = new UserRepositoryJdbcImpl(dataSource);

            Optional<User> ownerOpt = userRep.getUserById(st.getInt("owner"));
            User owner;
            if (ownerOpt.isPresent())
                owner = ownerOpt.get();
            else
                return Optional.empty();
            Chatroom room = new Chatroom(st.getInt("id"), owner, st.getString("roomname"));
            return Optional.of(room);

        } catch (SQLException e) {
            throw new ChatroomNotFoundException(e.getMessage());
        }
    }

}