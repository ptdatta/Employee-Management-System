package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogIn extends JFrame implements ActionListener {

    JTextField username;
    JPasswordField password;
    public LogIn() {
        super("Log In");
        setSize(780, 350);
        setLocation(500, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        Font f = new Font("Sans", Font.BOLD, 20);
        Font ef = new Font("Sans", Font.PLAIN, 20);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Resources/user.png"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(40, 20, 250, 250);
        add(image);

        JLabel lblusername = new JLabel("Username:");
        lblusername.setFont(f);
        lblusername.setBounds(370, 80, 200, 30);
        add(lblusername);

        username = new JTextField();
        username.setFont(ef);
        username.setBounds(500, 80, 200, 30);
        add(username);

        JLabel lblpassword = new JLabel("Password:");
        lblpassword.setFont(f);
        lblpassword.setBounds(370, 140, 200, 30);
        add(lblpassword);

        password = new JPasswordField();
        password.setFont(ef);
        password.setBounds(500, 140, 200, 30);
        add(password);

        JButton loginbtn = new JButton("LOGIN");
        loginbtn.setFont(f);
        loginbtn.setBounds(400, 220, 180, 40);
        loginbtn.setBackground(Color.BLACK);
        loginbtn.setForeground(Color.WHITE);
        loginbtn.addActionListener(this);
        add(loginbtn);
    }

    public static void main(String[] args) {
        LogIn logIn = new LogIn();
        logIn.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = username.getText();
        String pass = password.getText();
        if (user.equals("admin") && pass.equals("admin123")) {
            Dashboard dash = new Dashboard();
            dash.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid Username or Password");
        }
    }
}
