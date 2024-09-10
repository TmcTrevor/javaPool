package fr._42.chat.repositories;

import fr._42.chat.models.Chatroom;

import java.util.Optional;
import fr._42.chat.exceptions.ChatroomNotFoundException;

public interface ChatroomRepository {
    Optional<Chatroom> getRoomById(int id) throws ChatroomNotFoundException;
}