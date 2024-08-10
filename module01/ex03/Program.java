package ex03;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {

        User alice = new User("Alice", 1000.0f);
        User bob = new User("Bob", 500.0f);

        Transaction transaction1 = new Transaction(alice, bob, Transaction.TransferCategory.CREDITS, 700.0f);
        Transaction transaction2 = new Transaction(alice, bob, Transaction.TransferCategory.CREDITS, 1500.0f);
        Transaction transaction3 = new Transaction(bob, alice,Transaction.TransferCategory.DEBITS, -100.0f);
		// try {
        alice.addTransaction(transaction1);
        alice.addTransaction(transaction2);

        bob.addTransaction(transaction3);


        System.out.println("User 1 Transactions:");
        for (Transaction transaction : alice.getTransactions().toArray()) {
            System.out.println(transaction);
        }

        System.out.println("\nUser 2 Transactions:");
        for (Transaction transaction : bob.getTransactions().toArray()) {
            System.out.println(transaction);
        }

        // Attempt to remove a transaction
        try {
            alice.getTransactions().removeTransaction(transaction1.getIdentifier());
            System.out.println("\nAfter removing transaction1 from alice:");
            for (Transaction transaction : alice.getTransactions().toArray()) {
                System.out.println(transaction);
            }
        } catch (TransactionNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Attempt to remove a non-existent transaction
        try {
            bob.getTransactions().removeTransaction(UUID.randomUUID().toString()); // Random UUID
        } catch (TransactionNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
