package fr._42.chat.repositories;


import java.awt.image.RescaleOp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import fr._42.chat.models.User;
import org.postgresql.translation.messages_es;

import com.zaxxer.hikari.HikariDataSource;

import fr._42.chat.models.Chatroom;
import fr._42.chat.models.Message;

public class MessageRepositoryJdbcImpl implements MessageRepository {
    
    // private final HikariDataSource dataSource;
    private final DataSource dataSource;
    
    public MessageRepositoryJdbcImpl(DataSource hikariDS){
        dataSource = hikariDS;
    }
    
    @Override
    public Optional<Message> findById(Long id)
    {
        String SQL_QUERY = """
                SELECT m.id AS "message_id", m.* , u.id AS "userID", u.*, c.id as "roomID", c.*, cr.id AS "CreatorID", cr.login AS "creatorLogin", cr.password AS "creatorPassword"
                      FROM "Message" m
                      JOIN "User" u ON m.author = u.id\s
                      JOIN "Chatroom" c ON m.room = c.id
                      JOIN "User" cr ON c.owner = cr.id  -- Joining the creator of the room
                      WHERE m.id = ?
                """;
        try (Connection con = dataSource.getConnection();
            PreparedStatement pst = con.prepareStatement( SQL_QUERY )) {
                pst.setLong(1, id);
            ResultSet message = pst.executeQuery();
                if (message.next())
                {
                    User author = new User(message.getInt("userID"), message.getString("login"), message.getString("password"));
                    User roomOwner = new User(message.getInt("CreatorID"),  message.getString("creatorLogin"), message.getString("creatorPassword"));
                    Chatroom chatroom = new Chatroom(message.getInt("roomID"),roomOwner, message.getString("roomname"));
                    Message message1 = new Message(message.getInt("message_id"), author, chatroom, message.getString("text"), message.getTime("datetime"));
                    return Optional.of(message1);
                }

            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void saveMessage(Message message)
    {

    }
}
