package Customer;
import Bank.Bank;
import Transactions.TransFileHandler;
import Transactions.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CustomerHandler {

    CustomerFileHandler handler = new CustomerFileHandler();

    public void addCustomer() throws IOException
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your Name: \n");
        String name = sc.nextLine();

        System.out.print("Enter your Password: \n");
        String pass = sc.nextLine();

        if(isValid(pass))
        {
            System.out.print("Re-enter your password: \n");
            String repass = sc.nextLine();
            if(pass.equals(repass))
            {
                int n=0;
                long a=0;
                if(Bank.datas.size()==0)
                {
                    n=1;
                    a=4001;
                }else
                {
                    n = Bank.datas.getLast().customerId;
                    a = Bank.datas.getLast().accountId;
                }
                String s=encryptedPass(pass);
                Customer c = new Customer(n+1,a+1,name,Bank.balance,s);
                Bank.datas.add(c);
                Bank.customerMap.put(n+1,c);
                handler.writeData(c);
                logTransaction(n+1);

            }
            else
            {
                System.out.println("Your password doesnot matches..");
            }
        }
        else
        {
            System.out.println("Your Password should be mix of only alphabets and numbers!");
        }
    }
    public void logTransaction(int id)
    {
        Transaction t = new Transaction(1,"opening",10000,10000);
        TransFileHandler fileHandler = new TransFileHandler();
        fileHandler.writeTrans(id,t);
    }


    public static boolean isValid(String s)
    {
        char []c = s.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (!(c[i] >= 'A' && c[i] <= 'Z') &&  // A-Z
                    !(c[i] >= 'a' && c[i] <= 'z') &&  // a-z
                    !(c[i] >= '0' && c[i] <= '9')) {  // 0-9
                return false;
            }
        }
        return true;
    }

    public static String encryptedPass(String s)
    {
        char[] c=s.toCharArray();
        for(int i=0;i<c.length;i++)
        {
            char ct=c[i];
            char ans=switch(ct)
            {
                case 'Z'->'A';
                case 'z'->'a';
                case '9'->'0';
                default -> c[i]=(char)(c[i]+1);
            };
            c[i]= ans;
        }
        String x =String.valueOf(c);
        return x;
    }

    public boolean validUser(int a,String p)
    {
       p=encryptedPass(p);
       Customer c = Bank.customerMap.get(a);
       System.out.println(c);
       if(c==null)
       {
           System.out.println("No such user!");
           return false;
       }
       if(p.equals(c.password))
       {
           System.out.println("Good Afternoon!");
           return true;
       }
       return false;
    }

    public static void deposit(int id,double n)
    {

        Customer c = Bank.customerMap.get(id);
        if(c==null)
        {
            System.out.println("Invalid user!..");
        }
        if(n>0)
        {
            double b =c.balance;
            c.balance=b+n;
            Bank.customerMap.put(id,c);
            System.out.println("Your Previous Balance: "+b);
            System.out.println("Your Current Balance: "+c.balance);
            TransFileHandler t = new TransFileHandler();
            int transId = t.getLastTransId(c.customerId);
            Transaction trans=new Transaction(++transId,"deposit",n,c.balance);
            t.writeTrans(transId,trans);
        }
        else
        {
            System.out.println("Invalid amount");
        }
    }
    public static boolean withdraw(int id,double n)
    {
        if(n>0)
        {
            Customer c = Bank.customerMap.get(id);

            double b = c.balance;
            System.out.println("Your Previous Balance: " + b);
            if (n <= b - 1000) {
                b = b - n;
                c.balance=b;
                System.out.println("Your Current Balance: " + b);
                Bank.customerMap.put(id,c);
                TransFileHandler t = new TransFileHandler();
                int transId = t.getLastTransId(c.customerId);
                Transaction trans =new Transaction(++transId,"withdraw",n,c.balance);
                t.writeTrans(transId,trans);
                return true;
            } else
            {
                System.out.println("Insufficient Balance");
            }
        }
        else {
            System.out.println("Enter the valid amount!");
        }
        return false;
    }
    public static void transaction(int to,int from,double amount)
    {
        Customer c = Bank.customerMap.get(to);
        if(c==null)
        {
            System.out.println("No Such customer exist!..");
        }
        if (withdraw(from, amount)) {
            deposit(to, amount);
            System.out.println("Transaction completed successfully!!...");
        }
        else
        {
            System.out.println("You have not enough balance");
        }
    }








}
