public class UsersArrayList implements UsersList {


    private User[] users;
    private int size;

    private int  maxSize = 10;


    
    public UsersArrayList() {
        users = new User[maxSize];
        size = 0;
    }
    


    @Override
    public void addUser(User user) {

        if (users.length == maxSize)
            resizeArray();
        
        users[size++] = user;

        // throw new UnsupportedOperationException("Not supported yet.");
    }

    public void resizeArray()
    {
        int newSize = maxSize + maxSize / 2;


        User[] tmpUsers = new User[newSize];
        for (int i = 0;i < size; i++) // can be done with System.arraycopy
            tmpUsers[i] = users[i];
        users = tmpUsers;
        maxSize = newSize;
    }

       @Override
    public User getUserById(int id) {
        for (int i = 0; i < size; i++) {
            if (users[i].getIdentifier() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User with ID " + id + " not found.");
    }

    @Override
    public User getUserByIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds.");
        }
        return users[index];
    }

    @Override
    public int getNbrUsers() {
        return size;
    }
        
}