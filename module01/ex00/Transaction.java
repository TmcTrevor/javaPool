
package ex00;
import java.util.UUID;


public class Transaction {

    private String identifier;
    private User recipient;
    private User sender;
    private TransferCategory transferCategory;
    private float transferAmount;

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

    // toString method
    @Override
    public String toString() {
        return "Transaction : { \n \tidentifier='" + identifier + '\''
                + "\n \t recipient=" + recipient
                + "\n \t sender=" + sender
                + "\n \t transferCategory=" + transferCategory
                + "\n \t transferAmount=" + transferAmount
                + "\n }";
    }

    // Enum for TransferCategory
    public enum TransferCategory {
        DEBITS, CREDITS
    }
}
