package com.company;

import java.util.ArrayList;
import java.util.List;

public class Worker extends Employee {

    public Worker(String empID, String name, String designation, Department dept, int salary) {
        super(empID, name, designation, dept, salary);
    }

    @Override
    public int getTotalSalary() {
        return salary;
    }

    @Override
    public int getNoOfEmployees() {
        return 1;
    }

    @Override
    public List<Employee> getAllemployees() {
        List<Employee> empList=new ArrayList<Employee>();
        empList.add(this);
        return empList;
    }
}
