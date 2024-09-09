package fr._42.chat.repositories;
import java.util.Optional;

import fr._42.chat.models.Message;
public interface MessageRepository {
    Optional<Message> findById(Long id);
    void save(Message message) throws NotSavedSubEntityException;
    
}