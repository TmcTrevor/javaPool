public interface ChatroomRepository {
    Optional<Chatroom> getRoomById() throws ChatRoomNotFoundException;
}