package Bank;
import Customer.Customer;
import java.util.ArrayList;
import java.util.HashMap;

public class Bank {

    public static ArrayList<Customer> datas = new ArrayList();
    public static HashMap<Integer,Customer> customerMap = new HashMap<Integer, Customer>();
    public static int customer_id=1;
    public static long account_no=400l;
    public static final double balance=10000;

}
