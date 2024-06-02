package Transactions;
 public class Transaction {

    public int transId;
    public String type;
    public double amount;
    public double balance;

    public Transaction(int transId,String type,double amount,double balance)
    {
        this.transId=transId;
        this.type=type;
        this.amount=amount;
        this.balance=balance;
    }


    public String toString()
    {
        return transId +"\t"+ type +"\t"+ amount +"\t"+ balance;
    }

}
