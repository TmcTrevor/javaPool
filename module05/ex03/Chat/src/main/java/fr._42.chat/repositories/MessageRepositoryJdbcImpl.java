package fr._42.chat.repositories;


import java.sql.*;
import java.util.Optional;

import javax.sql.DataSource;

import fr._42.chat.exceptions.NotSavedSubEntityException;
import fr._42.chat.models.User;

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
                    Message message1 = new Message(message.getInt("message_id"), author, chatroom, message.getString("text"), message.getDate("datetime"));
                    return Optional.of(message1);
                }

            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void save(Message message)
    {
        try {
            String Query = "INSERT INTO \"Message\" (author, room, text, datetime) VALUES (?,?,?,?)";
            PreparedStatement pst = getPreparedStatement(message, Query);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    message.setId(generatedId);
                }
            }
//            ResultSet rs = pst.executeQuery();
//            if (rs.next())
//            {
//                int generatedId = rs.getInt("id");
//                message.setId(generatedId);
//                System.out.println("Inserted message ID: " + generatedId);
//            }
        } catch (RuntimeException e) {
            throw new NotSavedSubEntityException(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void update(Message message) throws NotSavedSubEntityException {
        try {
            String Query = "UPDATE \"Message\" SET author = ?, room = ? , text = ? , datetime = ? WHERE id = ?";
            PreparedStatement pst = getPreparedStatement(message, Query);
            pst.setInt(5,message.getId());
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = pst.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    message.setId(generatedId);
                }
            }
//            ResultSet rs = pst.executeQuery();
//            if (rs.next())
//            {
//                int generatedId = rs.getInt("id");
//                message.setId(generatedId);
//                System.out.println("Inserted message ID: " + generatedId);
//            }
        } catch (RuntimeException e) {
            throw new NotSavedSubEntityException(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement getPreparedStatement(Message message, String Query) throws SQLException {
        try {
            UserRepositoryJdbcImpl UserRep = new UserRepositoryJdbcImpl(dataSource);
            Optional<User> userOpt = UserRep.getUserById(message.getAuthor().getId());
            ChatroomRepositoryJdbcImpl chatroomRep = new ChatroomRepositoryJdbcImpl(dataSource);
            Optional<Chatroom> chatroomOpt = chatroomRep.getRoomById(message.getRoom().getId());
            Connection con = dataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(Query,  Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, message.getAuthor().getId());
            pst.setInt(2, message.getRoom().getId());
            pst.setString(3, message.getText());
            pst.setDate(4, message.getDatetime());
            return pst;
        } catch (RuntimeException e) {
            throw new NotSavedSubEntityException(e.getMessage());
        }
    }



}
