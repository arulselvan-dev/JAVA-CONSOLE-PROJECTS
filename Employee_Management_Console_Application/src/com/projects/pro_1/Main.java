package com.projects.pro_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Employee Management System");
        Employee_Doa_Int inter =new Employee_Dao_cla();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("1.Add Employee\n"+
                    "2.Show All Employee\n"+
                    "3.Show Employee Based On id\n"+
                    "4.Update the employee\n"+
                    "5.Delete the employee\n");
            System.out.println("Enter the number: ");
            int n = sc.nextInt();
            switch(n)
            {
                case 1:
                    Employee emp = new Employee();
                    System.out.println("Enter the id:");
                    int id =sc.nextInt();
                    System.out.println("Enter the name:");
                    String name =sc.next();
                    System.out.println("Enter the Salary:");
                    double salary=sc.nextDouble();
                    System.out.println("Enter the age:");
                    int age = sc.nextInt();
                     emp.setId(id);
                     emp.setName(name);
                     emp.setSalary(salary);
                     emp.setAge(age);
                    inter.createEmp(emp);
                    break;
                case 2:
                      inter.showAllEmp();
                      break;
                case 3:
                    System.out.println("Enter the Employee id:");
                    int i = sc.nextInt();
                    inter.ShowEmpBasedOnId(i);
                    break;
                case 4:
                    System.out.println("Enter the id:");
                    n =sc.nextInt();
                    System.out.println("Enter the name:");
                    String s = sc.next();
                    inter.updateEmp(n,s);
                    break;
                case 5:
                    System.out.println("Enter the id to be deleted:");
                    n=sc.nextInt();
                    inter.deleteEmp(n);
                    break;
                case 6:
                    System.out.println("ThankYou for using our application !!!");
                    System.exit(0);
                default:
                    System.out.println("Wrong");
            }

           }while(true);
    }
}
