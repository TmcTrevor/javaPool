public interface UserRepository {
    Optional<User> getUserById() throws UserNotFoundException;

}