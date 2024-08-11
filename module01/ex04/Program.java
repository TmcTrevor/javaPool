package ex04;

public class Program {
    public static void main(String[] args) {

		UsersArrayList usersList = new UsersArrayList();
		TransactionsService service = new TransactionsService(usersList);
        User alice = new User("Alice", 1000.0f);
        User bob = new User("Bob", 500.0f);

		service.addUser(bob);
		service.addUser(alice);
		try {
			service.addUser(bob); // will throw an error ( user exist)
		} catch (Exception e) {
			System.err.println("Error : "+  e.getMessage());
		}

		System.out.println(bob.getName() + "'s Balance : " + service.retriveBalance(bob.getIdentifier()));
		System.out.println(alice.getName() + "'s Balance : " + service.retriveBalance(alice.getIdentifier()));

		try {
		service.makeTransaction(alice.getIdentifier(), bob.getIdentifier(), 1500.0f); // will throw an error
		} catch (IllegalTransactionException e)
		{
			System.err.println("Error : "+ e.getMessage());
		}

		service.makeTransaction(alice.getIdentifier(), bob.getIdentifier(), 400.0f);

		System.out.println(bob.getName() + "'s Balance after transaction 1 : " + service.retriveBalance(bob.getIdentifier()));
		System.out.println(alice.getName() + "'s Balance after transaction 1 : " + service.retriveBalance(alice.getIdentifier()));
		// service.makeTransaction(.getIdentifier(), bob.getIdentifier(), 1500.0f);

		service.makeTransaction(bob.getIdentifier(), alice.getIdentifier(), 800.0f);
		service.makeTransaction(bob.getIdentifier(), alice.getIdentifier(), 100.0f);
		service.makeTransaction(alice.getIdentifier(), bob.getIdentifier(), 150.0f);

		System.out.println(bob.getName() + "'s Balance after transaction 2 : " + service.retriveBalance(bob.getIdentifier()));
		System.out.println(alice.getName() + "'s Balance after transaction 2 : " + service.retriveBalance(alice.getIdentifier()));

		System.out.println("List of transaction for bob");

		for (Transaction transaction : service.retriveUserTransactions(bob.getIdentifier())) {
            System.out.println(transaction.getTransferCategory() + " of " + transaction.getTransferAmount());
        }
		System.out.println("List of transaction for alice");
        for (Transaction transaction : service.retriveUserTransactions(alice.getIdentifier())) {
            System.out.println(transaction.getTransferCategory() + " of " + transaction.getTransferAmount());
        }

		service.removeTransaction(bob.getIdentifier(), service.retriveUserTransactions(bob.getIdentifier())[2].getIdentifier());
		service.removeTransaction(alice.getIdentifier(), service.retriveUserTransactions(alice.getIdentifier())[4].getIdentifier());


		System.out.println("List of transaction for bob after delete");

		for (Transaction transaction : service.retriveUserTransactions(bob.getIdentifier())) {
            System.out.println(transaction.getTransferCategory() + " of " + transaction.getTransferAmount());
        }
		System.out.println("List of transaction for alice after delete");
        for (Transaction transaction : service.retriveUserTransactions(alice.getIdentifier())) {
            System.out.println(transaction.getTransferCategory() + " of " + transaction.getTransferAmount());
        }

		Transaction[] unpTransactions = service.checkUnpairedTransactions();

		for (Transaction transaction : unpTransactions)
		{
			System.out.println(transaction);
		}


	}
}
