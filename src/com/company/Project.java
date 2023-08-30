package com.company;

import java.util.Random;
import java.util.UUID;

public class Project {

    String projId;
    String name;
    Department dept;
    String empId;


    public Project(String projId,String name, Department dept, String empId) {
        this.projId = projId;
        this.name = name;
        this.dept = dept;
        this.empId = empId;
    }
}
