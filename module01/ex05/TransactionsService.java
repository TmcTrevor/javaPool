package ex05;

public class TransactionsService {

	private final UsersArrayList users;

	public TransactionsService(UsersArrayList users) {
        this.users = users;
    }

	public void addUser(User user)
	{
		// if (users.getUserById(user.getIdentifier()))
		try {
			users.getUserById(user.getIdentifier());
			throw new RuntimeException("User Already Exist");
		} catch (UserNotFoundException e)
		{
			users.addUser(user);
		}
	}

	public float retriveBalance(int id)
	{
		try {
			float balance = users.getUserById(id).getBalance();
			return balance;
		} catch (UserNotFoundException e)
		{

			System.err.println("Error while retrieving Balance "+ e.getMessage());
			return 0;
		}
	}


	public User getUserById(int id)
	{
		return users.getUserById(id);
	}

	public Transaction getTransactionById(int userId, String id)
	{
		return users.getUserById(userId).getTransactions().retrieveTransaction(id);
	}

	public void makeTransaction(int recipientId, int senderId, float transferAmount)
	{
		User sender = users.getUserById(senderId);
        User recipient = users.getUserById(recipientId);

        if (sender.getBalance() < transferAmount) {
            throw new IllegalTransactionException("Insufficient balance for transfer.");
        }
        Transaction debitTransaction = new Transaction(recipient, sender, Transaction.TransferCategory.DEBITS, -transferAmount);
        Transaction creditTransaction = new Transaction(recipient, sender, Transaction.TransferCategory.CREDITS, transferAmount);
        creditTransaction.setIdentifier(debitTransaction.getIdentifier());
		sender.addTransaction(debitTransaction);
        recipient.addTransaction(creditTransaction);
        sender.setBalance(sender.getBalance() - transferAmount);
        recipient.setBalance(recipient.getBalance() + transferAmount);
	}

	public Transaction[] retriveUserTransactions(int userId)
	{
		return users.getUserById(userId).getTransactions().toArray();
	}

	public void removeTransaction(int userId, String transactionId)
	{
		users.getUserById(userId).getTransactions().removeTransaction(transactionId);

	}

	private Transaction[] getAllTransactions() {
		Transaction[] allTransactions = new Transaction[0]; // Assuming a max of 100 transactions for simplicity
        // Collect all transactions from all users
        for (int i = 0; i < users.getNbrUsers(); i++) {
            User user = users.getUserById(i + 1); // Assuming user IDs start from 1
            Transaction[] userTransactions = user.getTransactions().toArray();
			Transaction[]  tmp = new Transaction[allTransactions.length + userTransactions.length];
			int j = 0;
			for (Transaction transaction : allTransactions)
				tmp[j++] = transaction;
            for (Transaction transaction : userTransactions) {
                tmp[j++] = transaction;
            }
			allTransactions = tmp;
        }

		return allTransactions;
	}

	public Transaction[] checkUnpairedTransactions() {

        Transaction[] allTransactions = getAllTransactions();
        int transactionCount = allTransactions.length;


        Transaction[] unpairedTransactions = new Transaction[transactionCount];
        int unpairedCount = 0;

        for (int i = 0; i < transactionCount; i++) {
            boolean isPaired = false;

            for (int j = 0; j < transactionCount; j++) {
                if (i != j && allTransactions[i].getIdentifier().equals(allTransactions[j].getIdentifier())) {
                    if ((allTransactions[i].getTransferCategory() == Transaction.TransferCategory.CREDITS &&
                         allTransactions[j].getTransferCategory() == Transaction.TransferCategory.DEBITS) ||
                        (allTransactions[i].getTransferCategory() == Transaction.TransferCategory.DEBITS &&
                         allTransactions[j].getTransferCategory() == Transaction.TransferCategory.CREDITS)) {
                        isPaired = true;
                        break;
                    }
                }
            }

            if (!isPaired) {
                unpairedTransactions[unpairedCount++] = allTransactions[i];
            }
        }


        // Return the unpaired transactions as an array with exact size
        Transaction[] result = new Transaction[unpairedCount];
        for (int i = 0; i < unpairedCount; i++) {
            result[i] = unpairedTransactions[i];
        }
        return result;
    }
}
