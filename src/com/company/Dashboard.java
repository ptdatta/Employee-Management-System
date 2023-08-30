package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Dashboard extends JFrame implements ActionListener {
    DataBase db;
    JTabbedPane togglePane = new JTabbedPane();
    String[] arr;

    JTextField e_tEmp,e_tName,e_tDesignation,e_tSalary;
    JComboBox<String> e_tDept,p_tDept;
    DefaultComboBoxModel<String> e_deptModel,p_deptModel;
    JTextField d_tDept,d_tName,d_tAddress;
    JTextField p_tProj,p_tName,p_tEmpId;
    String[][] e_data,d_data,p_data;
    JTable e_jt,d_jt,p_jt;
    DefaultTableModel e_model,d_model,p_model;
    JScrollPane e_sp,d_sp,p_sp;
    JTextField e_searchField,d_searchField,p_searchField;
    Department d;
    Employee e;
    Project p;
    JLabel totalSalary,subordinaries,subNames,totalEmp,totalProj;
    JLabel emptodept,projtodept;


    public Dashboard(){
        db = DataBase.getInstance();
        db.loadData();
        this.setTitle("Dashboard");
        setExtendedState(MAXIMIZED_BOTH);
        setLocation(500, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        Font f = new Font("Sans", Font.BOLD, 30);
        Font ef = new Font("Sans", Font.PLAIN, 20);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JPanel panel = new JPanel();
        panel.setBackground(new Color(3, 12, 43));
        panel.setBounds(0, 0, 400, screenSize.height);
        panel.setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Resources/company.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50, 30, 250, 250);
        panel.add(image);

        JLabel lblusername = new JLabel("ABC Company Ltd.");
        lblusername.setBounds(50, 300, 500, 40);
        lblusername.setFont(f);
        lblusername.setForeground(Color.WHITE);
        panel.add(lblusername);


        ImageIcon depIcon = new ImageIcon(ClassLoader.getSystemResource("Resources/employees.png"));
        ImageIcon dep = new ImageIcon(depIcon.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT)); ;
        JButton depbtn = new JButton(" Employees",dep);
        depbtn.setFont(f);
        depbtn.setBorder(new RoundedBorder(25));
        depbtn.setBounds(50, 510, 400, 55);
        depbtn.setHorizontalAlignment(SwingConstants.LEFT);
        depbtn.setOpaque(false);
        depbtn.setContentAreaFilled(false);
        depbtn.setForeground(Color.WHITE);
        depbtn.addActionListener(this);
        panel.add(depbtn);

        ImageIcon empIcon = new ImageIcon(ClassLoader.getSystemResource("Resources/department.png"));
        ImageIcon emp = new ImageIcon(empIcon.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT)); ;
        JButton empbtn = new JButton(" Departments",emp);
        empbtn.setFont(f);
        empbtn.setBorder(new RoundedBorder(25));
        empbtn.setBounds(50, 430, 400, 55);
        empbtn.setHorizontalAlignment(SwingConstants.LEFT);
        empbtn.setOpaque(false);
        empbtn.setContentAreaFilled(false);
        empbtn.setForeground(Color.WHITE);
        empbtn.addActionListener(this);
        panel.add(empbtn);

        ImageIcon projIcon = new ImageIcon(ClassLoader.getSystemResource("Resources/project.png"));
        ImageIcon proj = new ImageIcon(projIcon.getImage().getScaledInstance(35,35,Image.SCALE_DEFAULT)); ;
        JButton projbtn = new JButton(" Projects",proj);
        projbtn.setFont(f);
        projbtn.setBorder(new RoundedBorder(25));
        projbtn.setBounds(50, 590, 400, 55);
        projbtn.setHorizontalAlignment(SwingConstants.LEFT);
        projbtn.setOpaque(false);
        projbtn.setContentAreaFilled(false);
        projbtn.setForeground(Color.WHITE);
        projbtn.addActionListener(this);
        panel.add(projbtn);

        add(panel);



        JPanel empPanel = new JPanel();
        JPanel deptPanel = new JPanel();
        JPanel projPanel = new JPanel();
        togglePane.setBackground(Color.WHITE);
        togglePane.setBounds(400,-20, screenSize.width-400, screenSize.height);
        togglePane.add("Department",deptPanel);
        togglePane.add("Employee",empPanel);
        togglePane.add("Project",projPanel);

//        EmpPanel Design
        empPanel.setLayout(new BorderLayout());
        empPanel.setBackground(Color.WHITE);


        JPanel emp_panel = new JPanel();
        emp_panel.setLayout(new BorderLayout());
        emp_panel.setBorder(new EmptyBorder(20,20,20,20));
        emp_panel.setBackground(Color.WHITE);

        ImageIcon e_i1 = new ImageIcon(ClassLoader.getSystemResource("Resources/user.png"));
        Image e_i2 = e_i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon e_i3 = new ImageIcon(e_i2);
        JLabel e_image = new JLabel(e_i3);
        e_image.setBounds(20, 30, 250, 250);
        emp_panel.add(e_image,BorderLayout.WEST);

        JPanel e_form = new JPanel();
        e_form.setLayout(new BoxLayout(e_form,BoxLayout.Y_AXIS));
        e_form.setBorder(new EmptyBorder(20,40,20,20));
        e_form.setBackground(Color.WHITE);

        totalEmp = new JLabel();
        totalEmp.setText("Total No of Employees: "+db.getEmployees().length);
        totalEmp.setFont(f);
        e_form.add(totalEmp);

        JPanel empField = new JPanel();
        empField.setBackground(Color.WHITE);
        empField.setLayout(new FlowLayout(10));
        JLabel lempId = new JLabel("Employee Id: ");
        lempId.setFont(ef);
        empField.add(lempId);
        e_tEmp = new JTextField();
        e_tEmp.setFont(ef);
        e_tEmp.setPreferredSize(new Dimension(300,45));
        empField.add(e_tEmp);
        e_form.add(empField);

        JPanel nameField = new JPanel();
        nameField.setBackground(Color.WHITE);
        nameField.setLayout(new FlowLayout(10));
        JLabel lempName = new JLabel("Name: ");
        lempName.setFont(ef);
        nameField.add(lempName);
        e_tName = new JTextField();
        e_tName.setFont(ef);
        e_tName.setPreferredSize(new Dimension(300,45));
        nameField.add(e_tName);
        e_form.add(nameField);

        JPanel designationField = new JPanel();
        designationField.setBackground(Color.WHITE);
        designationField.setLayout(new FlowLayout(10));
        JLabel lempDesignation = new JLabel("Designation: ");
        lempDesignation.setFont(ef);
        designationField.add(lempDesignation);
        e_tDesignation = new JTextField();
        e_tDesignation.setFont(ef);
        e_tDesignation.setPreferredSize(new Dimension(300,45));
        designationField.add(e_tDesignation);
        e_form.add(designationField);

        JPanel deptField = new JPanel();
        deptField.setBackground(Color.WHITE);
        deptField.setLayout(new FlowLayout(10));
        JLabel lempDeptId = new JLabel("Dept Id: ");
        lempDeptId.setFont(ef);
        deptField.add(lempDeptId);
        arr=db.getDepartmentNames();
        e_deptModel = new DefaultComboBoxModel<>(arr);
        e_tDept = new JComboBox<>(e_deptModel);
        e_tDept.setFont(ef);
        e_tDept.setPreferredSize(new Dimension(300,45));
        deptField.add(e_tDept);
        e_form.add(deptField);

        JPanel salaryField = new JPanel();
        salaryField.setBackground(Color.WHITE);
        salaryField.setLayout(new FlowLayout(10));
        JLabel lempSalary = new JLabel("Salary: ");
        lempSalary.setFont(ef);
        salaryField.add(lempSalary);
        e_tSalary = new JTextField();
        e_tSalary.setFont(ef);
        e_tSalary.setPreferredSize(new Dimension(300,45));
        salaryField.add(e_tSalary);
        e_form.add(salaryField);

        totalSalary = new JLabel();
        totalSalary.setFont(ef);
        totalSalary.setPreferredSize(new Dimension(300,45));
        e_form.add(totalSalary);

        subordinaries = new JLabel();
        subordinaries.setFont(ef);
        subordinaries.setPreferredSize(new Dimension(300,45));
        e_form.add(subordinaries);

        subNames = new JLabel();
        subNames.setFont(ef);
        subNames.setPreferredSize(new Dimension(300,45));
        e_form.add(subNames);


        emp_panel.add(e_form,BorderLayout.CENTER);

        JPanel e_btnPanel = new JPanel();
        e_btnPanel.setBackground(Color.WHITE);
        e_btnPanel.setLayout(new FlowLayout(10,10,10));
        e_btnPanel.setBorder(new EmptyBorder(20,20,20,20));

        e_searchField = new JTextField();
        e_searchField.setFont(ef);
        e_searchField.setPreferredSize(new Dimension(400,45));
        e_searchField.setForeground(Color.GRAY);
        e_searchField.setText("Enter Employee Id");
        e_searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                e_searchField.setText("");
                e_searchField.setForeground(Color.BLACK);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (e_searchField.getText().isEmpty()) {
                    e_searchField.setForeground(Color.GRAY);
                    e_searchField.setText("Enter Employee Id");
                }
            }
        });
        e_btnPanel.add(e_searchField);

        JButton e_btnSearch = new JButton("Search");
        e_btnSearch.setActionCommand("e_search");
        e_btnSearch.setFont(ef);
        e_btnSearch.setBackground(Color.BLACK);
        e_btnSearch.setForeground(Color.WHITE);
        e_btnSearch.addActionListener(this);
        e_btnPanel.add(e_btnSearch);


        JButton e_btnAdd = new JButton("+ Create");
        e_btnAdd.setActionCommand("e_add");
        e_btnAdd.setText("+Create");
        e_btnAdd.setFont(ef);
        e_btnAdd.setBackground(Color.BLACK);
        e_btnAdd.setForeground(Color.WHITE);
        e_btnAdd.addActionListener(this);
        e_btnPanel.add(e_btnAdd);

        JButton e_btnUpdate = new JButton("Update");
        e_btnUpdate.setActionCommand("e_update");
        e_btnUpdate.setFont(ef);
        e_btnUpdate.setBackground(Color.BLACK);
        e_btnUpdate.setForeground(Color.WHITE);
        e_btnUpdate.addActionListener(this);
        e_btnPanel.add(e_btnUpdate);

        JButton e_btnDelete = new JButton("Delete");
        e_btnDelete.setActionCommand("e_delete");
        e_btnDelete.setFont(ef);
        e_btnDelete.setBackground(Color.RED);
        e_btnDelete.setForeground(Color.WHITE);
        e_btnDelete.addActionListener(this);
        e_btnPanel.add(e_btnDelete);

        emp_panel.add(e_btnPanel,BorderLayout.SOUTH);

        empPanel.add(emp_panel,BorderLayout.NORTH);

        e_data = db.getEmployees();
        String[] e_columns = new String[]{"Employee Id", "Name", "Designation", "Department Id", "Salary"};
        e_model = new DefaultTableModel(e_data,e_columns);
        e_jt = new JTable(e_model);
        e_jt.setRowHeight(30);
        e_jt.getTableHeader().setBackground(new Color(3, 12, 43));
        e_jt.getTableHeader().setForeground(Color.WHITE);
        e_jt.getTableHeader().setFont(new Font("Serif", Font.BOLD, 30));
        e_jt.setFont(ef);
        e_sp = new JScrollPane(e_jt);
        empPanel.add(e_sp);

//        Department Panel Design
        deptPanel.setBackground(Color.WHITE);
        deptPanel.setLayout(new BorderLayout());

        JPanel dept_panel = new JPanel();
        dept_panel.setLayout(new BorderLayout());
        dept_panel.setBorder(new EmptyBorder(20,20,20,20));
        dept_panel.setBackground(Color.WHITE);

        ImageIcon d_i1 = new ImageIcon(ClassLoader.getSystemResource("Resources/deptpane.png"));
        Image d_i2 = d_i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon d_i3 = new ImageIcon(d_i2);
        JLabel d_image = new JLabel(d_i3);
        d_image.setBounds(20, 30, 250, 250);
        dept_panel.add(d_image,BorderLayout.WEST);

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form,BoxLayout.Y_AXIS));
        form.setBorder(new EmptyBorder(20,40,20,20));
        form.setBackground(Color.WHITE);

        JPanel d_deptField = new JPanel();
        d_deptField.setBackground(Color.WHITE);
        d_deptField.setLayout(new FlowLayout(10));
        JLabel d_lempId = new JLabel("Department Id: ");
        d_lempId.setFont(ef);
        d_deptField.add(d_lempId);
        d_tDept = new JTextField();
        d_tDept.setFont(ef);
        d_tDept.setPreferredSize(new Dimension(300,45));
        d_deptField.add(d_tDept);
        form.add(d_deptField);

        JPanel d_nameField = new JPanel();
        d_nameField.setBackground(Color.WHITE);
        d_nameField.setLayout(new FlowLayout(10));
        JLabel d_lempName = new JLabel("Name: ");
        d_lempName.setFont(ef);
        d_nameField.add(d_lempName);
        d_tName = new JTextField();
        d_tName.setFont(ef);
        d_tName.setPreferredSize(new Dimension(300,45));
        d_nameField.add(d_tName);
        form.add(d_nameField);

        JPanel addressField = new JPanel();
        addressField.setBackground(Color.WHITE);
        addressField.setLayout(new FlowLayout(10));
        JLabel lempAddress = new JLabel("Address: ");
        lempAddress.setFont(ef);
        addressField.add(lempAddress);
        d_tAddress = new JTextField();
        d_tAddress.setFont(ef);
        d_tAddress.setPreferredSize(new Dimension(300,45));
        addressField.add(d_tAddress);
        form.add(addressField);

        emptodept = new JLabel();
        emptodept.setFont(ef);
//        emptodept.setText("hello");
        form.add(emptodept);

        projtodept = new JLabel();
        projtodept.setFont(ef);
//        projtodept.setText("hello");
        form.add(projtodept);


        dept_panel.add(form,BorderLayout.CENTER);

        JPanel d_btnPanel = new JPanel();
        d_btnPanel.setBackground(Color.WHITE);
        d_btnPanel.setLayout(new FlowLayout(10,10,10));
        d_btnPanel.setBorder(new EmptyBorder(20,20,20,20));

        d_searchField = new JTextField();
        d_searchField.setFont(ef);
        d_searchField.setPreferredSize(new Dimension(400,45));
        d_searchField.setForeground(Color.GRAY);
        d_searchField.setText("Enter Department Name");
        d_searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                d_searchField.setText("");
                d_searchField.setForeground(Color.BLACK);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (d_searchField.getText().isEmpty()) {
                    d_searchField.setForeground(Color.GRAY);
                    d_searchField.setText("Enter Department Name");
                }
            }
        });
        d_btnPanel.add(d_searchField);

        JButton d_btnSearch = new JButton("Search");
        d_btnSearch.setActionCommand("d_search");
        d_btnSearch.setFont(ef);
        d_btnSearch.setBackground(Color.BLACK);
        d_btnSearch.setForeground(Color.WHITE);
        d_btnSearch.addActionListener(this);
        d_btnPanel.add(d_btnSearch);


        JButton d_btnAdd = new JButton("+ Create");
        d_btnAdd.setActionCommand("d_add");
        d_btnAdd.setFont(ef);
        d_btnAdd.setBackground(Color.BLACK);
        d_btnAdd.setForeground(Color.WHITE);
        d_btnAdd.addActionListener(this);
        d_btnPanel.add(d_btnAdd);

        JButton d_btnUpdate = new JButton("Update");
        d_btnUpdate.setActionCommand("d_update");
        d_btnUpdate.setFont(ef);
        d_btnUpdate.setBackground(Color.BLACK);
        d_btnUpdate.setForeground(Color.WHITE);
        d_btnUpdate.addActionListener(this);
        d_btnPanel.add(d_btnUpdate);

        JButton d_btnDelete = new JButton("Delete");
        d_btnDelete.setActionCommand("d_delete");
        d_btnDelete.setFont(ef);
        d_btnDelete.setBackground(Color.RED);
        d_btnDelete.setForeground(Color.WHITE);
        d_btnDelete.addActionListener(this);
        d_btnPanel.add(d_btnDelete);

        dept_panel.add(d_btnPanel,BorderLayout.SOUTH);

        deptPanel.add(dept_panel,BorderLayout.NORTH);

        d_data = db.getDepartments();
        String[] d_columns = new String[]{"Department Id", "Name", "Address"};
        d_model = new DefaultTableModel(d_data,d_columns);
        d_jt = new JTable(d_model);
        d_jt.setRowHeight(30);
        d_jt.getTableHeader().setBackground(new Color(3, 12, 43));
        d_jt.getTableHeader().setForeground(Color.WHITE);
        d_jt.getTableHeader().setFont(new Font("Serif", Font.BOLD, 30));
        d_jt.setFont(ef);
        d_sp = new JScrollPane(d_jt);
        deptPanel.add(d_sp);

//       Project Panel Design
        projPanel.setLayout(new BorderLayout());
        projPanel.setBackground(Color.WHITE);

        JPanel p_panel = new JPanel();
        p_panel.setLayout(new BorderLayout());
        p_panel.setBorder(new EmptyBorder(20,20,20,20));
        p_panel.setBackground(Color.WHITE);

        ImageIcon p_i1 = new ImageIcon(ClassLoader.getSystemResource("Resources/projpane.png"));
        Image p_i2 = p_i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon p_i3 = new ImageIcon(p_i2);
        JLabel p_image = new JLabel(p_i3);
        p_image.setBounds(20, 30, 250, 250);
        p_panel.add(p_image,BorderLayout.WEST);

        JPanel p_form = new JPanel();
        p_form.setLayout(new BoxLayout(p_form,BoxLayout.Y_AXIS));
        p_form.setBorder(new EmptyBorder(20,40,20,20));
        p_form.setBackground(Color.WHITE);

        totalProj = new JLabel();
        totalProj.setText("Total no of Projects: "+db.getProjects().length);
        totalProj.setFont(f);
        p_form.add(totalProj);

        JPanel projField = new JPanel();
        projField.setBackground(Color.WHITE);
        projField.setLayout(new FlowLayout(10));
        JLabel p_lempId = new JLabel("Proj Id: ");
        p_lempId.setFont(ef);
        projField.add(p_lempId);
        p_tProj = new JTextField();
        p_tProj.setFont(ef);
        p_tProj.setPreferredSize(new Dimension(300,45));
        projField.add(p_tProj);
        p_form.add(projField);

        JPanel p_nameField = new JPanel();
        p_nameField.setBackground(Color.WHITE);
        p_nameField.setLayout(new FlowLayout(10));
        JLabel p_lempName = new JLabel("Name: ");
        p_lempName.setFont(ef);
        p_nameField.add(p_lempName);
        p_tName = new JTextField();
        p_tName.setFont(ef);
        p_tName.setPreferredSize(new Dimension(300,45));
        p_nameField.add(p_tName);
        p_form.add(p_nameField);

        JPanel p_deptField = new JPanel();
        p_deptField.setBackground(Color.WHITE);
        p_deptField.setLayout(new FlowLayout(10));
        JLabel p_lempDeptId = new JLabel("Dept Id: ");
        p_lempDeptId.setFont(ef);
        p_deptField.add(p_lempDeptId);
        arr=db.getDepartmentNames();
        p_deptModel = new DefaultComboBoxModel<>(arr);
        p_tDept = new JComboBox<>(p_deptModel);
        p_tDept.setFont(ef);
        p_tDept.setPreferredSize(new Dimension(300,45));
        p_deptField.add(p_tDept);
        p_form.add(p_deptField);


        JPanel p_empField = new JPanel();
        p_empField.setBackground(Color.WHITE);
        p_empField.setLayout(new FlowLayout(10));
        JLabel p_lempDesignation = new JLabel("Emp ID(Team Lead): ");
        p_lempDesignation.setFont(ef);
        p_empField.add(p_lempDesignation);
        p_tEmpId = new JTextField();
        p_tEmpId.setFont(ef);
        p_tEmpId.setPreferredSize(new Dimension(300,45));
        p_empField.add(p_tEmpId);
        p_form.add(p_empField);

        p_panel.add(p_form,BorderLayout.CENTER);

        JPanel p_btnPanel = new JPanel();
        p_btnPanel.setBackground(Color.WHITE);
        p_btnPanel.setLayout(new FlowLayout(10,10,10));
        p_btnPanel.setBorder(new EmptyBorder(20,20,20,20));

        p_searchField = new JTextField();
        p_searchField.setFont(ef);
        p_searchField.setForeground(Color.GRAY);
        p_searchField.setText("Enter Project Id");
        p_searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                p_searchField.setText("");
                p_searchField.setForeground(Color.BLACK);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (p_searchField.getText().isEmpty()) {
                    p_searchField.setForeground(Color.GRAY);
                    p_searchField.setText("Enter Project Id");
                }
            }
        });
        p_searchField.setPreferredSize(new Dimension(400,45));
        p_btnPanel.add(p_searchField);

        JButton p_btnSearch = new JButton("Search");
        p_btnSearch.setActionCommand("p_search");
        p_btnSearch.setFont(ef);
        p_btnSearch.setBackground(Color.BLACK);
        p_btnSearch.setForeground(Color.WHITE);
        p_btnSearch.addActionListener(this);
        p_btnPanel.add(p_btnSearch);


        JButton p_btnAdd = new JButton("+ Create");
        p_btnAdd.setActionCommand("p_add");
        p_btnAdd.setFont(ef);
        p_btnAdd.setBackground(Color.BLACK);
        p_btnAdd.setForeground(Color.WHITE);
        p_btnAdd.addActionListener(this);
        p_btnPanel.add(p_btnAdd);

        JButton p_btnUpdate = new JButton("Update");
        p_btnUpdate.setActionCommand("p_update");
        p_btnUpdate.setFont(ef);
        p_btnUpdate.setBackground(Color.BLACK);
        p_btnUpdate.setForeground(Color.WHITE);
        p_btnUpdate.addActionListener(this);
        p_btnPanel.add(p_btnUpdate);

        JButton p_btnDelete = new JButton("Delete");
        p_btnDelete.setActionCommand("p_delete");
        p_btnDelete.setFont(ef);
        p_btnDelete.setBackground(Color.RED);
        p_btnDelete.setForeground(Color.WHITE);
        p_btnDelete.addActionListener(this);
        p_btnPanel.add(p_btnDelete);

        p_panel.add(p_btnPanel,BorderLayout.SOUTH);

        projPanel.add(p_panel,BorderLayout.NORTH);

        p_data = db.getProjects();
        String[] p_columns = new String[]{"Project Id", "Name","Department Id", "Emp Id(Team Lead)"};
        p_model = new DefaultTableModel(p_data,p_columns);
        p_jt = new JTable(p_model);
        p_jt.setRowHeight(30);
        p_jt.getTableHeader().setBackground(new Color(3, 12, 43));
        p_jt.getTableHeader().setForeground(Color.WHITE);
        p_jt.getTableHeader().setFont(new Font("Serif", Font.BOLD, 30));
        p_jt.setFont(ef);
        p_sp = new JScrollPane(p_jt);
        projPanel.add(p_sp);
        
        add(togglePane);

    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        String cmd = event.getActionCommand();
        d_jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                int row = d_jt.getSelectedRow();
                d_tDept.setText(d_model.getValueAt(row,0).toString());
                d_tName.setText(d_model.getValueAt(row,1).toString());
                d_tAddress.setText(d_model.getValueAt(row,2).toString());
                emptodept.setText("No of Employees: "+db.getEmpinDept(db.getDepartment(d_model.getValueAt(row,1).toString())));
                projtodept.setText("No of Projects: "+db.getProjinDept(db.getDepartment(d_model.getValueAt(row,1).toString())));
            }
        });
        e_jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                int row = e_jt.getSelectedRow();
                e_tEmp.setText(e_model.getValueAt(row,0).toString());
                e= db.getEmployee(e_tEmp.getText());
                totalSalary.setText("Total Salary: "+e.getTotalSalary());
                subNames.setText("No of Subordinaries: "+e.getNoOfEmployees());
                String s = "";
                List<Employee> list = e.getAllemployees();
                for(int i=0;i< list.size()-1;i++){
                    s+=list.get(i).name+",";
                }
                s+=list.get(list.size()-1).name;
                subordinaries.setText("Subordinaries: "+s);
                e_tName.setText(e_model.getValueAt(row,1).toString());
                e_tDesignation.setText(e_model.getValueAt(row,2).toString());
                e_tDept.setSelectedItem(e_model.getValueAt(row,3).toString());
                e_tSalary.setText(e_model.getValueAt(row,4).toString());
            }
        });
        p_jt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                int row = p_jt.getSelectedRow();
                p_tProj.setText(p_model.getValueAt(row,0).toString());
                p_tName.setText(p_model.getValueAt(row,1).toString());
                p_tDept.setSelectedItem(p_model.getValueAt(row,3).toString());
                p_tEmpId.setText(p_model.getValueAt(row,3).toString());
            }
        });
        if(cmd.equals(" Departments")){
            db.refresh();
            togglePane.setSelectedIndex(0);
        } else if (cmd.equals(" Employees")) {
            db.refresh();
            togglePane.setSelectedIndex(1);
        } else if (cmd.equals(" Projects")) {
            db.refresh();
            togglePane.setSelectedIndex(2);
        } else if (cmd.equals("d_search")) {
            String stId=d_searchField.getText();
            d = db.getDepartment(stId);
            if(d == null){
                JOptionPane.showMessageDialog(this,"No such department found");
            }
            else{
                d_tDept.setText(d.deptId);
                d_tName.setText(d.name);
                d_tAddress.setText(d.address);
                emptodept.setText("No of Employees: "+db.getEmpinDept(db.getDepartment(d.name)));
                projtodept.setText("No of Projects: "+db.getProjinDept(db.getDepartment(d.name)));
            }
        } else if (cmd.equals("d_add")) {
            Department d=new Department(d_tDept.getText(),d_tName.getText(),d_tAddress.getText());
            db.addDepartment(d);
            d_model.addRow(new Object[]{d.deptId,d.name,d.address});
            d_model.fireTableChanged(null);
            db.refresh();
            System.out.println("Department Created "+d.deptId);
        } else if (cmd.equals("d_update")) {
            int row = d_jt.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(this,"Please select a row to update");
            }
            else{
                d = new Department(d_tDept.getText(),d_tName.getText(),d_tAddress.getText());
                db.updateDepartment(d);
                db.refresh();
                d_model.setValueAt(d_tDept.getText(),row,0);
                d_model.setValueAt(d_tName.getText(),row,1);
                d_model.setValueAt(d_tAddress.getText(),row,2);
                d_model.fireTableChanged(null);
                arr = db.getDepartmentNames();
                e_tDept.setModel(new DefaultComboBoxModel(arr));
                System.out.println("Department Updated "+d.deptId);
            }
        } else if (cmd.equals("d_delete")) {
            int row = d_jt.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(this,"Please select a row to delete");
            }
            else{
                d_model.removeRow(row);
                db.removeDepartment(d_tName.getText());
                d_model.fireTableChanged(null);
                db.refresh();
                d_tDept.setText("");
                d_tName.setText("");
                d_tAddress.setText("");
                arr = db.getDepartmentNames();
                e_tDept.setModel(new DefaultComboBoxModel(arr));
                System.out.println("Department Deleted "+d.deptId);
            }
        } else if (cmd.equals("e_search")) {
            String stId=e_searchField.getText();
            e = db.getEmployee(stId);
            if(e == null){
                JOptionPane.showMessageDialog(this,"No such employee found");
            }
            else{
                e_tEmp.setText(e.empID);
                e_tName.setText(e.name);
                e_tDesignation.setText(e.designation);
                e_tDept.setSelectedItem(e.dept.name);
                e_tSalary.setText(String.valueOf(e.salary));
                totalSalary.setText("Total Salary: "+e.getTotalSalary());
                subNames.setText("No of Subordinaries: "+e.getNoOfEmployees());
                String s = "";
                List<Employee> list = e.getAllemployees();
                for(int i=0;i< list.size()-1;i++){
                    s+=list.get(i).name+",";
                }
                s+=list.get(list.size()-1).name;
                subordinaries.setText("Subordinaries: "+s);
            }
        } else if (cmd.equals("e_add")) {
            if(e_tDesignation.getText().equals("Worker")){
                e = new Worker(e_tEmp.getText(),e_tName.getText(),e_tDesignation.getText(),db.getDepartment(e_tDept.getSelectedItem().toString()),Integer.parseInt(e_tSalary.getText()));
            }else {
                e = new Leader(e_tEmp.getText(),e_tName.getText(),e_tDesignation.getText(),db.getDepartment(e_tDept.getSelectedItem().toString()),Integer.parseInt(e_tSalary.getText()));
            }
            db.addEmployee(e);
            e_model.addRow(new Object[]{e.empID,e.name,e.designation,e.dept.name,e.salary});
            db.refresh();
            totalEmp.setText("Total No of Employees: "+db.getEmployees().length);
            System.out.println("Employee Created "+e.empID);
        } else if (cmd.equals("e_update")) {
            int row = e_jt.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(this,"Please select a row to update");
            }
            else{
                if(e_tDesignation.getText().equals("Worker")){
                    e = new Worker(e_tEmp.getText(),e_tName.getText(),e_tDesignation.getText(),db.getDepartment(e_tDept.getSelectedItem().toString()),Integer.parseInt(e_tSalary.getText()));
                }else {
                    e = new Leader(e_tEmp.getText(),e_tName.getText(),e_tDesignation.getText(),db.getDepartment(e_tDept.getSelectedItem().toString()),Integer.parseInt(e_tSalary.getText()));
                }
                db.updateEmployee(e);
                db.refresh();
                e_model.setValueAt(e_tEmp.getText(),row,0);
                e_model.setValueAt(e_tName.getText(),row,1);
                e_model.setValueAt(e_tDesignation.getText(),row,2);
                e_model.setValueAt(e_tDept.getSelectedItem(),row,3);
                e_model.setValueAt(e_tSalary.getText(),row,4);
                e_model.fireTableChanged(null);
            }
        } else if (cmd.equals("e_delete")) {
            int row = e_jt.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(this,"Please select a row to delete");
            }
            else{
                e_model.removeRow(row);
                db.removeEmployee(e_tEmp.getText());
                db.refresh();
                totalEmp.setText("Total No of Employees: "+db.getEmployees().length);
                e_model.fireTableChanged(null);
                e_tEmp.setText("");
                e_tName.setText("");
                e_tDesignation.setText("");
                e_tSalary.setText("");
            }
        } else if (cmd.equals("p_search")) {
            String stId=p_searchField.getText();
            p = db.getProject(stId);
            if(p == null){
                JOptionPane.showMessageDialog(this,"No such project found");
            }
            else{
                p_tProj.setText(p.projId);
                p_tName.setText(p.name);
                p_tDept.setSelectedItem(p.dept.name);
                p_tEmpId.setText(String.valueOf(p.empId));
            }
        } else if (cmd.equals("p_add")) {
            p = new Project(p_tProj.getText(),p_tName.getText(),db.getDepartment(p_tDept.getSelectedItem().toString()),p_tEmpId.getText());
            db.addProject(p);
            db.refresh();
            totalProj.setText("Total no of Projects: "+db.getProjects().length);
            p_model.addRow(new Object[]{p.projId,p.name,p.dept.name,p.empId});
            p_model.fireTableChanged(null);
        } else if (cmd.equals("p_update")) {
            int row = p_jt.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(this,"Please select a row to update");
            }
            else{
                p = new Project(p_tProj.getText(),p_tName.getText(),db.getDepartment(p_tDept.getSelectedItem().toString()),p_tEmpId.getText());
                db.updateProject(p);
                db.refresh();
                p_model.setValueAt(p_tProj.getText(),row,0);
                p_model.setValueAt(p_tName.getText(),row,1);
                p_model.setValueAt(p_tDept.getSelectedItem(),row,2);
                p_model.setValueAt(p_tEmpId.getText(),row,3);
                p_model.fireTableChanged(null);
            }
        } else if (cmd.equals("p_delete")) {
            int row = p_jt.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(this,"Please select a row to delete");
            }
            else{
                p_model.removeRow(row);
                db.removeProject(p_tProj.getText());
                db.refresh();
                totalProj.setText("Total no of Projects: "+db.getProjects().length);
                p_model.fireTableChanged(null);
                p_tProj.setText("");
                p_tName.setText("");
                p_tEmpId.setText("");
            }
        }
    }
}
