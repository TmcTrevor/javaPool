package ex05;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private final TransactionsService service;
    private final boolean isDevMode;

    public Menu(boolean isDevMode) {
        this.service = new TransactionsService(new UsersArrayList());
        this.isDevMode = isDevMode;
    }
	public Menu(TransactionsService service, boolean isDevMode) {
        this.service = service;
        this.isDevMode = isDevMode;
    }

    public void showMenu() {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice = -1;

            while (choice != 0) {
                System.out.println("1. Add User");
                System.out.println("2. View User Balances");
                System.out.println("3. Perform A Transfer");
                System.out.println("4. View all transactions for a specific user");
                if (isDevMode) {
                    System.out.println("5. DEV - remove a transfer by ID");
                    System.out.println("6. DEV - check transfer validity");
                    System.out.println("7. Finish execution");
                }
                else
                    System.out.println("5. Finish execution");
                System.out.print("-> ");

                try {
                    choice = scanner.nextInt();
                    // choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1 -> addUser(scanner);
                        case 2 -> viewUserBalance(scanner);
                        case 3 -> addTransaction(scanner);
                        case 4 -> viewTransactions(scanner);
                        case 5 -> {
                            if (isDevMode) removeTransactionByUserId(scanner);
                            else System.exit(0);
                        }
                        case 6 -> {
                            if (isDevMode) validateAllTransactions();
                            else System.out.println("Invalid choice. Please enter a number from the menu.");
                        }
                        case 7 -> System.exit(0);
                        default -> System.out.println("Invalid choice. Please enter a number from the menu.");

                    }
					System.out.println("---------------------------------------------------------");
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number.");
					choice = -1;
					scanner.nextLine();
					// scanner.close();
                } catch (Exception e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }
            }
        }
    }

	private void addUser(Scanner scanner)
	{
		try {
			System.out.println("\tEnter a user name and a balance");
			System.out.print("\t-> ");
			String name = scanner.next();
			int balance = scanner.nextInt();
			// System.err.println(" hhhhhhhh = " + name + " b === " + balance);
			User user = new User(name, balance);
			service.addUser(user);
			System.out.println("\t\tUser with id = "+ user.getIdentifier() + " is added");
		}
	catch (InputMismatchException e) {
        System.err.println("\tError: Please enter a valid number for the balance.");
        scanner.nextLine(); // Consume the invalid input
    }
		catch (Exception e)
		{

			System.err.println("\tError On user add : " + e.getMessage());

		}
	}

	private  void viewUserBalance(Scanner scanner)
	{
		try {
			System.out.println("\tEnter User ID");
			System.out.print("\t-> ");
			int id = scanner.nextInt();
			System.out.println("\t" + service.getUserById(id).getName() + " - " +service.retriveBalance(id));

		} catch (RuntimeException e) {
			System.err.println("\tError On View Balance : " + e.getMessage());
		}
	}

    private void addTransaction(Scanner scanner) {
        try {

            System.out.println("\tEnter a sender ID, a recipient ID, and a transfer amount");
			System.out.print("\t-> ");
            int senderId = scanner.nextInt();
            int recipientId = scanner.nextInt();
            float amount = scanner.nextFloat();
			service.makeTransaction(recipientId, senderId, amount);

            // service.addTransaction(new Transaction(userId, amount));
            System.out.println("\t\tThe Transfer is completed.");
        } catch (NumberFormatException e) {
            System.out.println("\tInvalid input. Please enter numbers only.");
        }
    }

    private void viewTransactions(Scanner scanner) {
		try {
		System.out.println("\tEnter a user ID");
		System.out.print("\t-> ");
		int userId = scanner.nextInt();
        Transaction[]  userTransaction = service.retriveUserTransactions(userId);

		for (Transaction transaction : userTransaction)
		{
			System.out.println(transaction);
		}
		if (userTransaction.length == 0)
			System.out.println("User Have No TRANSACTION");
	} catch (Exception e)
	{
		System.err.println("Error on view Transactions : " + e.getMessage());
	}
	}

    private void removeTransactionByUserId(Scanner scanner) {
        try {
            System.out.println("Enter a user ID and a transfer ID");
            System.out.print("-> ");
            int userId = scanner.nextInt();
            String transactionId = scanner.next();
			Transaction transaction = service.getTransactionById(userId, transactionId);
            service.removeTransaction(userId, transactionId);
			String toFrom;
			String name;
			int id;
			if (transaction.getTransferCategory().equals(Transaction.TransferCategory.DEBITS))
			{
				toFrom = "To ";
				name = transaction.getRecipient().getName();
				id = transaction.getRecipient().getIdentifier();
			}
			else {
				toFrom = "From ";
				name = transaction.getSender().getName();
				id = transaction.getSender().getIdentifier();
			}
            System.out.println("Transfer " + toFrom + name + "(id = "+id +")" + transaction.getTransferAmount() + " removed");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid user ID.");
        }
    }

    private void validateAllTransactions() {
		try {
			Transaction[] transactions = service.checkUnpairedTransactions();
			for (Transaction transaction : transactions)
			{
				if (transaction.getTransferCategory().equals(Transaction.TransferCategory.DEBITS))
					System.out.println(transaction.getSender().getName() + "(id = " + transaction.getSender().getIdentifier() + ") has an unacknowledged transfer id = " + transaction.getIdentifier() + " to " + transaction.getRecipient().getName() + "(id = "+transaction.getRecipient().getIdentifier() + ")  of " + transaction.getTransferAmount());
				else
					System.out.println(transaction.getRecipient().getName() + "(id = " + transaction.getRecipient().getIdentifier() + ") has an unacknowledged transfer id = " + transaction.getIdentifier() + "from  " + transaction.getRecipient().getName() + "(id = "+transaction.getRecipient().getIdentifier() + ")  for " + transaction.getTransferAmount());
			}
		}
		catch (RuntimeException e) {
			System.err.println("Error On check Validation : " +  e.getMessage());
		}
    }
}
