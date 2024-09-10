package fr._42.chat.repositories;

import fr._42.chat.exceptions.UserNotFoundException;
import fr._42.chat.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserById(int id) throws UserNotFoundException;
    List<User> findAll(int page, int size);

}