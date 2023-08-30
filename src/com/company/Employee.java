package com.company;

import java.util.List;


public abstract class Employee {

    String empID;
    String name;
    String designation;
    Department dept;
    int salary;

    public Employee(String empID,String name, String designation, Department dept, int salary) {
        this.empID = empID;
        this.name = name;
        this.designation = designation;
        this.dept = dept;
        this.salary = salary;
    }

    public abstract int getTotalSalary();

    public abstract int getNoOfEmployees();

    public abstract List<Employee> getAllemployees();

}
