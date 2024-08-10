package ex01;
public class User {

    private final int identifier;
    private String name;
    private float balance;

    // Constructor
    public User(String name, float balance) {
        this.identifier = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.balance = balance;
    }

    // Default constructor
    public User() {
        this.identifier = UserIdsGenerator.getInstance().generateId();
    }

    // Getters and Setters
    public int getIdentifier() {
        return identifier;
    }

    // public void setIdentifier(int identifier) {
    //     this.identifier = identifier;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.balance = balance;
    }

    // toString method
    @Override
    public String toString() {
        return "User{"
                + "identifier=" + identifier
                + ", name='" + name + '\''
                + ", balance=" + balance
                + '}';
    }

    // equals and hashCode methods (if needed for comparing objects)
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (identifier != user.identifier) {
            return false;
        }
        if (Float.compare(user.balance, balance) != 0) {
            return false;
        }
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = identifier;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (balance != +0.0f ? Float.floatToIntBits(balance) : 0);
        return result;
    }
}
