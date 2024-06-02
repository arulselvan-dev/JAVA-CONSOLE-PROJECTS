package Customer;
import Bank.Bank;
import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class CustomerFileHandler {
    private static String filename = "bank_db.txt";

    public void initialize() throws IOException
    {

        File ref = new File(filename);
        BufferedReader reader = new BufferedReader(
                new FileReader(ref));

        String customerInfo= reader.readLine();
        do{
            Customer customerData = addData(customerInfo);
            Bank.datas.add(customerData);
            Bank.customerMap.put(customerData.customerId,customerData);
            customerInfo= reader.readLine();
        }while(customerInfo!=null);
        reader.close();
    }

    private Customer addData(String customerData)
    {
        String[] trimed = customerData.split(" ");
        Customer custom = new Customer(Integer.parseInt(trimed[0]),Long.parseLong(trimed[1]),trimed[2],Double.parseDouble(trimed[3]),trimed[4]);
        return  custom;
    }

    public void writeData(Customer customer)
    {
        FileWriter writer =null;
        try
        {
             writer=new FileWriter(filename,true);
             writer.write(customer.toString()+"\n");
        }catch (Exception e)
        {
           e.printStackTrace();
        }
        finally {
            if(writer!=null)
            {
                try {
                    writer.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void finalizeCustomerFile() {
        File file = new File(filename);
        FileWriter writers = null;

        try {
            writers = new FileWriter(file);
            Set key = Bank.customerMap.keySet();
            Iterator itr = key.iterator();

            while (itr.hasNext()) {
                int k = (int) itr.next();
                Customer c = Bank.customerMap.get(k);
                writers.write("\n" + c.toString());
            }
            writers.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the FileWriter is closed properly
            if (writers != null) {
                try {
                    writers.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
