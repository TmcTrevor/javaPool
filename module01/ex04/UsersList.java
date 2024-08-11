package ex04;
interface UsersList {
    public void addUser(User user);
    User getUserById(int id) throws UserNotFoundException;
    User getUserByIndex(int index) throws IndexOutOfBoundsException;
    public int  getNbrUsers();
}
