
package ex02;
public class Program {

    public static void main(String[] args) {
        UsersArrayList userList = new UsersArrayList();

        User user1 = new User("Alice", 1000.0f);
        User user2 = new User("Bob", 500.0f);
        User user3 = new User("Charlie", 200.0f);

        userList.addUser(user1);
        userList.addUser(user2);
        userList.addUser(user3);

        System.out.println("All users:");
        for (int i = 0; i < userList.getNbrUsers(); i++) {
            System.out.println(userList.getUserByIndex(i));
        }

        try {
            User user = userList.getUserById(2);
            System.out.println("User with ID 2: " + user);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            userList.getUserById(99); // This should throw an exception
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
