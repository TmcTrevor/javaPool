package ex00;
// import ex00.User;

public class Program {

    public static void main(String[] args) {
        // Create User objects with hardcoded data
		try {
        User alice = new User(1, "Alice", 1000.00f);
        User bob = new User(2, "Bob", 500.00f);


        System.out.println("Users:");
        System.out.println(alice);
        System.out.println(bob);

        Transaction transaction1 = new Transaction(alice, bob, Transaction.TransferCategory.CREDITS, 200.00f);
        Transaction transaction2 = new Transaction(alice, bob, Transaction.TransferCategory.DEBITS, -150.00f);

        System.out.println("Trasactions :");
        System.out.println(transaction1);
        System.out.println(transaction2);
		} catch (RuntimeException e)
		{

			System.err.println("Error :" + e.getMessage());
		}


		try {
			User wrongUser = new User(3, "Bob", -500.00f);
			System.out.println(wrongUser);
		} catch (RuntimeException e)
		{

			System.err.println("Error :" + e.getMessage());
		}
		try {
			User alice = new User(1, "Alice", 1000.00f);
			User bob = new User(2, "Bob", 500.00f);
			Transaction transaction1 = new Transaction(alice, bob, Transaction.TransferCategory.CREDITS, -200.00f);
			System.out.println(transaction1);
		} catch (RuntimeException e)
		{

			System.err.println("Error :" + e.getMessage());
		}

    }
}
