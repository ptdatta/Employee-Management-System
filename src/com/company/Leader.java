package com.company;

import java.util.ArrayList;
import java.util.List;

public class Leader extends Employee {

    List<Employee> subordinates=new ArrayList<Employee>();

    public Leader(String empid,String name, String designation, Department dept, int salary) {
        super(empid,name, designation, dept, salary);
    }

    public void add(Employee e) {
        subordinates.add(e);
    }

    public void remove(Employee e) {
        subordinates.remove(e);
    }

    @Override
    public int getTotalSalary() {
        int salary = this.salary;
        for(Employee e:subordinates){
            salary+=e.getTotalSalary();
        }
        return salary;
    }

    @Override
    public int getNoOfEmployees() {
        int cnt=1;
        for(Employee e:subordinates){
            cnt+=e.getNoOfEmployees();
        }
        return cnt;
    }

    @Override
    public List<Employee> getAllemployees() {
        List<Employee> empList=new ArrayList<Employee>();
        empList.add(this);
        for(Employee e:subordinates){
            empList.addAll(e.getAllemployees());
        }
        return empList;
    }
}
