package fr._42.repositories;

import fr._42.exceptions.EntityNotFoundException;
import fr._42.models.User;

public interface UsersRepository {
    User findByLogin(String login) throws EntityNotFoundException;
    void update(User user) throws EntityNotFoundException;
}
