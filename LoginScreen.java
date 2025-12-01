package oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginScreen() {
        setTitle("Car Management System - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setResizable(false);

        // Main panel 
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(52, 73, 94));

        // Title
        JLabel titleLabel = new JLabel("CAR MANAGEMENT SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(0, 30, 400, 30);
        panel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Please login to continue", SwingConstants.CENTER);
        subtitleLabel.setForeground(Color.LIGHT_GRAY);
        subtitleLabel.setBounds(0, 60, 400, 20);
        panel.add(subtitleLabel);

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(80, 100, 100, 25);
        panel.add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(80, 130, 240, 35);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(usernameField);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(80, 170, 100, 25);
        panel.add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(80, 200, 240, 35);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(130, 250, 140, 40);
        loginButton.setBackground(new Color(46, 204, 113));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(loginButton);

        // Action 
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.equals("admin") && password.equals("1234")) {
                    JOptionPane.showMessageDialog(null, 
                        "Login Successful! Welcome to Car Management System", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Close login window
                    new CarGUIMain(); // Open main system
                } else {
                    JOptionPane.showMessageDialog(null, 
                        "Invalid username or password!\nHint: admin / 1234", 
                        "Login Failed", JOptionPane.ERROR_MESSAGE);
                    passwordField.setText("");
                    passwordField.requestFocus();
                }
            }
        });
        add(panel);
        setVisible(true);
    }

    // Test login screen
    public static void main(String[] args) {
  
        new LoginScreen();
    }
}