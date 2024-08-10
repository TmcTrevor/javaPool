public class Program {
    public static void main(String[] args) {
        User user1 = new User(1, "Alice", 1000.0f);
        User user2 = new User(2, "Bob", 500.0f);

        Transaction transaction1 = new Transaction("Rent payment", -700.0f);
        Transaction transaction2 = new Transaction("Salary", 1500.0f);
        Transaction transaction3 = new Transaction("Groceries", -100.0f);

        user1.addTransaction(transaction1);
        user1.addTransaction(transaction2);

        user2.addTransaction(transaction3);

        System.out.println("User 1 Transactions:");
        for (Transaction transaction : user1.getTransactions().toArray()) {
            System.out.println(transaction);
        }

        System.out.println("\nUser 2 Transactions:");
        for (Transaction transaction : user2.getTransactions().toArray()) {
            System.out.println(transaction);
        }

        // Attempt to remove a transaction
        try {
            user1.getTransactions().removeTransactionById(transaction1.getId());
            System.out.println("\nAfter removing transaction1 from user1:");
            for (Transaction transaction : user1.getTransactions().toArray()) {
                System.out.println(transaction);
            }
        } catch (TransactionNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Attempt to remove a non-existent transaction
        try {
            user2.getTransactions().removeTransactionById(UUID.randomUUID()); // Random UUID
        } catch (TransactionNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
