package Customer;

public class Customer {
    public static String space=" ";
   public int customerId;
   public long accountId;
   public String name;
   public String password;
   public double balance;

    public Customer(int customerId,long accountId,String name,double balance,String password)
    {
        this.accountId=accountId;
        this.customerId=customerId;
        this.name=name;
        this.balance=balance;
        this.password=password;
    }

    public String toString()
    {
        return customerId+space+accountId+space+name+space+balance+space+password;
    }

}
