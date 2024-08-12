package ex05;
public class UserIdsGenerator {


    private static UserIdsGenerator instance;
    private int id;

    private UserIdsGenerator() {
        id = 0;
    }


    // ? Thread Safety: The generateId() method is synchronized to ensure thread safety when generating IDs, preventing race conditions in multi-threaded environments.
// ? Singleton Pattern: The getInstance() method ensures that only one instance of UserIdsGenerator exists. It checks if the instance is null and, if so, initializes it.
    public static synchronized UserIdsGenerator getInstance() {
        if (instance == null) {
            instance = new UserIdsGenerator();
        }
        return instance;
    }

    public synchronized int generateId() {
        id += 1;
        return id;
    }


    // toString method
    @Override
    public String toString() {
        return "UserIdsGenerator{"
                + "current Id=" + id
                + '}';
    }
}
