package com.company;

import java.io.*;

public class DataBase {

    private static DataBase db = null;
   private CustomHashMap<String,Employee> employeeMap;
   private CustomHashMap<String,Department> departmentMap;
   private CustomHashMap<String,Project> projectMap;

    private DataBase() {
         employeeMap = new CustomHashMap<>(50000);
         departmentMap = new CustomHashMap<>(100);
         projectMap = new CustomHashMap<>(100);
    }

    public static DataBase getInstance(){
        if(db == null){
            db = new DataBase();
        }
        return db;
    }

    public void addEmployee(Employee employee){
        employeeMap.insert(employee.empID,employee);
    }

    public void addDepartment(Department department){
        departmentMap.insert(department.name,department);
    }

    public void addProject(Project project){
        projectMap.insert(project.projId,project);
    }

    public Employee getEmployee(String empId){
        return employeeMap.get(empId);
    }

    public Department getDepartment(String deptId){
        return departmentMap.get(deptId);
    }

    public Project getProject(String projId){
        return projectMap.get(projId);
    }

    public void removeEmployee(String empId){
        employeeMap.delete(empId);
    }

    public void removeDepartment(String deptId){
        departmentMap.delete(deptId);
    }

    public void removeProject(String projId){
        projectMap.delete(projId);
    }

    public void updateEmployee(Employee employee){
        employeeMap.update(employee.empID,employee);
    }

    public void updateDepartment(Department department){
        departmentMap.update(department.name,department);
    }

    public void updateProject(Project project){
        projectMap.update(project.projId,project);
    }

    public CustomHashMap<String,Employee> getEmployeeMap(){
        return employeeMap;
    }

    public CustomHashMap<String,Department> getDepartmentMap(){
        return departmentMap;
    }

    public CustomHashMap<String,Project> getProjectMap(){
        return projectMap;
    }

    public String[][] getEmployees(){
        String[][] data = new String[employeeMap.getSize()][5];
        int i = 0;
        for(String key : employeeMap.getKeys(String.class)){
            Employee e = employeeMap.get(key);
            data[i][0] = e.empID;
            data[i][1] = e.name;
            data[i][2] = e.designation;
            data[i][3] = e.dept.name;
            data[i][4] = String.valueOf(e.salary);
            i++;
        }
        return data;
    }

    public String[][] getDepartments(){
        String[][] data = new String[departmentMap.getSize()][3];
        int i = 0;
        for(String key : departmentMap.getKeys(String.class)){
            Department d = departmentMap.get(key);
            data[i][0] = d.deptId;
            data[i][1] = d.name;
            data[i][2] = d.address;
            i++;
        }
        return data;
    }

    public String[][] getProjects(){
        String[][] data = new String[projectMap.getSize()][4];
        int i = 0;
        for(String key : projectMap.getKeys(String.class)){
            Project p = projectMap.get(key);
            data[i][0] = p.projId;
            data[i][1] = p.name;
            data[i][2] = p.dept.name;
            data[i][3] = p.empId;
            i++;
        }
        return data;
    }

    public String[] getDepartmentNames(){
        String[] data = new String[departmentMap.getSize()];
        int i = 0;
        for(String key : departmentMap.getKeys(String.class)){
            Department d = departmentMap.get(key);
            data[i] = d.name;
            i++;
        }
        return data;
    }

    public String getEmpinDept(Department d){
        String ans="";
        String[][] arr = getEmployees();
        for(int i=0;i< arr.length;i++){
            if (d.name.equals(arr[i][3])){
                ans+=arr[i][1]+",";
            }
        }
        return ans.substring(0,ans.length());
    }

    public String getProjinDept(Department d){
        String ans="";
        String[][] arr = getProjects();
        for(int i=0;i< arr.length;i++){
            if (d.name.equals(arr[i][2])){
                ans+=arr[i][1]+",";
            }
        }
        return ans.substring(0,ans.length());
    }

    public void addWorker(Employee l){
        for(String key : employeeMap.getKeys(String.class)){
            Employee e = employeeMap.get(key);
            if(e.dept.equals(l.dept) && e.designation.equals("Worker")){
                ((Leader) l).add(e);
            }
        }
    }

    public void loadData(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("src/data.txt"));
            while(br.ready()) {
                String str1 = br.readLine();
                String values[] = str1.split(",");
                if(values[0].equals("D")) {
                    Department d = new Department(values[1], values[2], values[3]);
                    db.addDepartment(d);
                } else if (values[0].equals("E")) {
                    if(values[3].equals("Worker")) {
                        Department d = db.getDepartment(values[4]);
                        Employee w = new Worker(values[1], values[2], values[3], d, Integer.parseInt(values[5]));
                        db.addEmployee(w);
                    } else  {
                        Department d = db.getDepartment(values[4]);
                        Employee l = new Leader(values[1], values[2], values[3], d, Integer.parseInt(values[5]));
                        db.addWorker(l);
                        db.addEmployee(l);
                    }
                } else if (values[0].equals("P")) {
                    Department d = db.getDepartment(values[3]);
                    Project p = new Project(values[1], values[2], d, values[4]);
                    db.addProject(p);
                }

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void refresh(){
        String[][] emps = this.getEmployees();
        String[][] depts = this.getDepartments();
        String[][] projs=this.getProjects();

        try (PrintWriter writer = new PrintWriter(new File("src/data.txt"))) {
            for(int i=0;i< depts.length;i++){
                writer.println("D,"+depts[i][0]+","+depts[i][1]+","+depts[i][2]);
            }
            for(int i=0;i< emps.length;i++){
                if(emps[i][2].equals("Worker")){
                    writer.println("E,"+emps[i][0]+","+emps[i][1]+","+emps[i][2]+","+emps[i][3]+","+emps[i][4]);
                }
            }
            for(int i=0;i< emps.length;i++){
                if(!emps[i][2].equals("Worker")){
                    writer.println("E,"+emps[i][0]+","+emps[i][1]+","+emps[i][2]+","+emps[i][3]+","+emps[i][4]);
                }
            }
            for(int i=0;i< projs.length;i++){
                writer.println("P,"+projs[i][0]+","+projs[i][1]+","+projs[i][2]+","+projs[i][3]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    
}
