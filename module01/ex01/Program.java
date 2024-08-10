
package ex01;
public class Program {

    public static void main(String[] args) {
        // Create User objects with hardcoded data
        User alice = new User("Alice", 1000.00f);
        UserIdsGenerator test = UserIdsGenerator.getInstance();
        User bob = new User("Bob", 500.00f);

        System.out.println("Users:");
        System.out.println(alice);
        System.out.println(bob);
        System.out.println(test);

    }
}
