package ex03;
// import java.util.LinkedList;
public class TransactionsLinkedList implements TransactionsList {

	// private LinkedList<Transaction> transactions;
	private Transaction head;


	public TransactionsLinkedList() {
		// transaction = new LinkedList<Transaction>();
		this.head = null;
	}


        @Override
	public void addTransaction(Transaction transaction)
	{
		// transactions.add(transaction);
		if (this.head == null)
			this.head = transaction;
		else
		{
			transaction.setNext(this.head);
			this.head = transaction;
		}
	}

        @Override
	public void removeTransaction(String id)
	{
		// boolean check = false;
		// for (Transaction tr : transactions)
		// {
		// 	if (tr.getIdentifier().equals(id))
		// 	{
		// 		transactions.remove(tr);
		// 		check = true;
		// 	}
		// }
		// if (check == false)
		// 	throw new TransactionNotFoundException("Transaction not found");

		if (head == null) {
			throw new TransactionNotFoundException("Transaction with ID " + id + " not found.");
		}

		if (head.getIdentifier().equals(id)) {
			head = head.getNext();
			return;
		}
		Transaction node = head;
		Transaction previousNode = null;
		while (node != null) {
			if (node.getIdentifier().equals(id)) {
				previousNode.setNext(node.getNext());
				return;
			}
			previousNode = node;
			node = node.getNext();
		}
		throw new TransactionNotFoundException("Transaction with ID " + id + " not found.");
	}

	@Override
    public Transaction[] toArray() {

		// return transactions.toArray();
        int size = getSize();
        Transaction[] transactionsArray = new Transaction[size];
        Transaction current = head;
        int index = 0;

        while (current != null) {
            transactionsArray[index++] = current;
            current = current.getNext();
        }

        return transactionsArray;
    }

    private int getSize() {
        int size = 0;
        Transaction current = head;

        while (current != null) {
            size++;
            current = current.getNext();
        }

        return size;
    }
}
