package fr._42.chat.repositories;

import fr._42.chat.exceptions.UserNotFoundException;
import fr._42.chat.models.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> getUserById(int id) throws UserNotFoundException;

}