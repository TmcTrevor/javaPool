package fr._42.chat.repositories;
import java.util.Optional;

import fr._42.chat.Message;
public interface MessageRepository {
    Optional<Message> findById(Long id);
    
}