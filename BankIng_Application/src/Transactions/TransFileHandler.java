package Transactions;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class TransFileHandler {

    public void writeTrans(int id,Transaction transaction)
    {
        String filename = id+".txt";
        try
        {
            File file =new File(filename);
            if(!file.exists())
            {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file,true);
            writer.write(transaction.toString());
            writer.close();

        }catch (Exception e)
        {
            System.out.println("Exception: "+e);
        }
    }
    public int getLastTransId(int customerId)
    {
        String filename = customerId+".txt";
        try
        {
            File file =new File(filename);
            if(!file.exists())
            {
                return 0;
            }
            FileReader reader = new FileReader(file);
            Scanner s = new Scanner(file);
            String str="";
            while(s.hasNext())
            {
                str=s.nextLine();
            }
            s.close();
            Transaction t = castString(str);
            return t.transId;

        }catch (Exception e)
        {
            System.out.println("Exception: "+e);
        }

        return 0;
    }
    private Transaction castString(String str)
    {
        String s[] = str.split("\t");
        return new Transaction(Integer.parseInt(s[0]),s[1],Double.parseDouble(s[2]),Double.parseDouble(s[3]));
    }
    public void printTransactionHistory(int id)
    {
        String filename = id+".txt";
        try {
            File file = new File(filename);
            if(!file.exists())
            {
                System.out.println("No transactions occur!");
            }
            FileReader reader = new FileReader(file);
            Scanner s = new Scanner(reader);
            while(s.hasNext())
            {
                System.out.println(s.nextLine()+"\n");
            }
         s.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
