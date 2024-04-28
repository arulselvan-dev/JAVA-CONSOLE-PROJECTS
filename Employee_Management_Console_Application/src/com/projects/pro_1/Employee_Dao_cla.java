package com.projects.pro_1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employee_Dao_cla implements Employee_Doa_Int{
    Connection con;
    @Override
    public void createEmp(Employee emp)
    {
        con = Data_Base.createConnection();
        String query="insert into employee values(?,?,?,?)";
        try
        {
            PreparedStatement psm = con.prepareStatement(query);
            psm.setInt(1,emp.getId());
            psm.setString(2,emp.getName());
            psm.setDouble(3,emp.getSalary());
            psm.setInt(4,emp.getAge());
            int count = psm.executeUpdate();
            if(count!=0)
            {
                System.out.println("Messagae inserted successfully...");
            }
            else
            {
                System.out.println("Error occured!");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void showAllEmp() {
        con=Data_Base.createConnection();
        String query ="select * from employee";
        System.out.println("Employee Details");
        System.out.printf("%s\t%s\t%s\t\t%s\n","id","Name","Salary","Age");
        try
        {
            Statement out =con.createStatement();
            ResultSet result = out.executeQuery(query);
            while(result.next())
            {
                System.out.format("%d\t%s\t%.2f\t%d\n",
                        result.getInt(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getInt(4));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    @Override
    public void ShowEmpBasedOnId(int id) {
        con = Data_Base.createConnection();
        String query ="select * from employee where id="+id;
        try
        {
           Statement st = con.createStatement();
           ResultSet result=st.executeQuery(query);
           while(result.next())
           {
               System.out.format("%d\t%s\t%f\t\t%d\n",
                       result.getInt(1),
                       result.getString(2),
                       result.getDouble(3),
                       result.getInt(4));
           }
        }catch(Exception e)
        {
           e.printStackTrace();   // It prints the type of error and indicates,at which line it is in it.
        }
    }

    @Override
    public void updateEmp(int id, String name) {

        con = Data_Base.createConnection();
        String query = "update employee set name=? where id=?";
        try
        {
            PreparedStatement pst = con.prepareStatement(query);  //Prepared Statement is used when we use the database as a dynamic one.
            pst.setString(1,name);
            pst.setInt(2,id);
            int ans = pst.executeUpdate();
            if(ans!=0)
            {
                System.out.println("Updated successfully..");
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmp(int id) {

        con =Data_Base.createConnection();
        String query ="delete from employee where id=?";
        try
        {
           PreparedStatement pst = con.prepareStatement(query);
           pst.setInt(1,id);
           int ans = pst.executeUpdate();
           if(ans!=0)
           {
               System.out.println("Deleted Successfully..");
           }
        }catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
