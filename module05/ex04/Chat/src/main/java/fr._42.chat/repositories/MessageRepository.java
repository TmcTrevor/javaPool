package fr._42.chat.repositories;
import java.util.Optional;

import fr._42.chat.exceptions.NotSavedSubEntityException;
import fr._42.chat.models.Message;
public interface MessageRepository {
    Optional<Message> findById(Long id);
    void save(Message message) throws NotSavedSubEntityException;
    void update(Message message) throws NotSavedSubEntityException;

//    void saveMessage(Message message);
}