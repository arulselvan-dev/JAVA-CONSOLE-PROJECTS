package com.projects.pro_1;
import java.sql.Connection;
import java.sql.DriverManager;

public class Data_Base {
    static Connection con;
    public static Connection createConnection()
    {
        try{
            //   load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //    get connection
            String url="jdbc:mysql://localhost:3306/employeeDB";
            String username="root";
            String password="Arul$07$!@#123";
            con = DriverManager.getConnection(url,username,password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }

}
