package ex03;
interface TransactionsList {
   public void addTransaction(Transaction transaction);
   public void removeTransaction(String id);
   public Transaction[] toArray();

}
