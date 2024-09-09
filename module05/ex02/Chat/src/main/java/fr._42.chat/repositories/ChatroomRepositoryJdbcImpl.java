package fr._42.chat.repositories;
public class ChatroomRepositoryJdbcImpl implements ChatroomRepository {
    private final DataSource dataSource;
    public ChatroomRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    Optional<Chatroom> getRoomById(int id)
    {
        String query = "SELECT * FROM chatrooms WHERE id = ?";
        try (Connection con = dataSource.getConnection(); PreparedStatement pst = con.prepareStatement(query)){
            pst.setInt(1, id);
            ResultSet st = pst.exectuteQuery();



        }
    }

}