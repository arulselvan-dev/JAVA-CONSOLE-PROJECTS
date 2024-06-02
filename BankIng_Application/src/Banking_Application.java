
import Customer.CustomerFileHandler;
import Customer.CustomerHandler;
import Transactions.TransFileHandler;

import java.util.Scanner;

public class Banking_Application {
    public static void main(String[] args){


        CustomerFileHandler file = new CustomerFileHandler();
        try {
            file.initialize();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        CustomerHandler handler = new CustomerHandler();
        boolean flag=true;
        while(flag)
        {
           System.out.println("1.Add Customer\n2.ValidateUser\n3.Deposit\n4.Withdraw\n5.Money Transfer\n6.Transaction History\n7.Exit");
           Scanner sc = new Scanner(System.in);
           int n=sc.nextInt();
           switch(n)
           {
               case 1:
                   try
                   {
                       handler.addCustomer();
                   }catch (Exception e)
                   {
                       e.printStackTrace();
                   }
                   break;
               case 2:
                   System.out.println("Enter your account num:");
                   int id = sc.nextInt();
                   System.out.println("Enter your password:");
                   String pass = sc.next();
                   handler.validUser(id,pass);
                   break;
               case 3:
                   System.out.println("Enter your account num:");
                   int userId = sc.nextInt();
                   System.out.println("Enter your password:");
                   String userPass = sc.next();

                   if(handler.validUser(userId,userPass))
                   {
                       System.out.println("Enter the amount to be deposit:");
                       double d =sc.nextInt();
                       handler.deposit(userId,d);
                   }
                   else
                   {
                       System.out.println("Invalid User!");
                   }

                   break;
               case 4:
                   System.out.println("Enter your account num:");
                   int userid = sc.nextInt();
                   System.out.println("Enter your password:");
                   String userpass = sc.next();
                   if(handler.validUser(userid,userpass))
                   {
                       System.out.println("Enter the amount to be withdraw:");
                       double dm =sc.nextInt();
                       handler.withdraw(userid,dm);
                   }
                   else
                   {
                       System.out.println("Invalid User!");
                   }
                   break;
               case 5:
                   System.out.println("Enter your account num:");
                   int myId = sc.nextInt();
                   System.out.println("Enter your password:");
                   String myPass = sc.next();
                   if(handler.validUser(myId,myPass))
                   {
                       System.out.println("Enter the customer id to send: ");
                       int toId=sc.nextInt();
                       System.out.println("Enter the amount to be send: ");
                       double amount=sc.nextInt();
                       handler.transaction(toId,myId,amount);
                   }
                   else
                   {
                       System.out.println("Invalid User!");
                   }
                   break;
               case 6:
                   System.out.println("Enter your account num:");
                   int transId = sc.nextInt();
                   System.out.println("Enter your password:");
                   String transPass = sc.next();
                   if(handler.validUser(transId,transPass))
                   {
                       TransFileHandler fileHandler = new TransFileHandler();
                       fileHandler.printTransactionHistory(transId);
                   }
                   else {
                       System.out.println("Invalid userId and Password!");
                   }
                   break;
               case 7:
                   flag=false;
                   break;
               default:
                   System.out.println("Thank You Visit Again!!!");
           }
        }
        file.finalizeCustomerFile();
    }
}
