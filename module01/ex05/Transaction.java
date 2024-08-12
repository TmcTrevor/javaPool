package ex05;
import java.util.UUID;

public class Transaction {

    private String identifier;
    private User recipient;
    private User sender;
    private TransferCategory transferCategory;
    private float transferAmount;
	private Transaction next;

    public Transaction(User recipient, User sender, TransferCategory transferCategory, float transferAmount) {
        this.identifier = UUID.randomUUID().toString(); // Generates a unique UUID string
        this.recipient = recipient;
        this.sender = sender;

        this.transferCategory = transferCategory;
        if (transferCategory == TransferCategory.CREDITS && transferAmount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive for credits.");
        }
        if (transferCategory == TransferCategory.DEBITS && transferAmount >= 0) {
            throw new IllegalArgumentException("Transfer amount must be negative for debits.");
        }
        this.transferAmount = transferAmount;
		this.next = null;
		// recipient.addTransaction(this);
		// sender.addTransaction(this);
    }

    // Getters and Setters
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public TransferCategory getTransferCategory() {
        return transferCategory;
    }

    public void setTransferCategory(TransferCategory transferCategory) {
        this.transferCategory = transferCategory;
    }

    public float getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(float transferAmount) {
        if (transferCategory == TransferCategory.CREDITS && transferAmount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive for credits.");
        }
        if (transferCategory == TransferCategory.DEBITS && transferAmount >= 0) {
            throw new IllegalArgumentException("Transfer amount must be negative for debits.");
        }
        this.transferAmount = transferAmount;
    }
	public Transaction getNext()
	{
		return this.next;
	}

	public void setNext(Transaction transaction)
	{
		this.next = transaction;
	}

    // toString method
    @Override
    public String toString() {
		 if (this.transferCategory.equals(Transaction.TransferCategory.DEBITS))
		 	return "To " + this.recipient.getName() + "(id = " + this.recipient.getIdentifier() + ") "+ this.transferAmount + " with id= " + this.identifier;
		else
		return "From " + this.sender.getName() + "(id = " + this.sender.getIdentifier() + ") "+ this.transferAmount + " with id= " + this.identifier;

        // return "Transaction{"
        //         + "identifier='" + identifier + '\''
        //         + ", recipient=" + recipient
        //         + ", sender=" + sender
        //         + ", transferCategory=" + transferCategory
        //         + ", transferAmount=" + transferAmount
        //         + '}';
    }

    // Enum for TransferCategory
    public enum TransferCategory {
        DEBITS, CREDITS
    }
}
