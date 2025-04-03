package org.example.views;

import org.example.controllers.SignUpController;
import org.example.services.UserService;
import org.example.views.base.Layout;

import javax.swing.*;
import java.awt.*;

public class Signup extends Layout {

    public JTextField nidText, nameText, phoneText, bloodGroupText, sexText, ageText, emergencyNumbersText, emergencyMailsText;
    public JPasswordField passwordText;
    public JButton registerButton;
    public JButton goToSignInButton;
    private SignUpController signUpController;
    private UserService userService;

    public Signup() {
        super("Sign Up");
        setContentPane(createForm());
        userService  = new UserService();
        signUpController = new SignUpController(this, userService);


    }

    private JPanel createForm() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(255, 255, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Sign Up", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(34, 193, 195));
        formPanel.add(titleLabel);
        formPanel.add(Box.createVerticalStrut(30));

        addLabelAndField(formPanel, "Nid:", nidText = new JTextField(20));
        addLabelAndField(formPanel, "Password:", passwordText = new JPasswordField(20));
        addLabelAndField(formPanel, "Name:", nameText = new JTextField(20));
        addLabelAndField(formPanel, "Phone:", phoneText = new JTextField(20));
        addLabelAndField(formPanel, "Blood Group:", bloodGroupText = new JTextField(20));
        addLabelAndField(formPanel, "Sex:", sexText = new JTextField(20));
        addLabelAndField(formPanel, "Age:", ageText = new JTextField(20));
        addLabelAndField(formPanel, "Emergency Numbers:", emergencyNumbersText = new JTextField(20));
        addLabelAndField(formPanel, "Emergency Emails:", emergencyMailsText = new JTextField(20));

        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 18));
        registerButton.setBackground(new Color(34, 193, 195));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        formPanel.add(registerButton);

        // "Go to Sign-In" button
        goToSignInButton = new JButton("Already have an account? Go to Sign-In");
        goToSignInButton.setFont(new Font("Arial", Font.PLAIN, 14));
        goToSignInButton.setBackground(new Color(200, 200, 200));
        formPanel.add(goToSignInButton);

        return formPanel;
    }

    private void addLabelAndField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 18));
        label.setForeground(new Color(70, 70, 70));
        panel.add(label);

        textField.setFont(new Font("Arial", Font.PLAIN, 18));
        textField.setPreferredSize(new Dimension(300, 40));
        textField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        textField.setBackground(Color.WHITE);
        textField.setCaretColor(new Color(34, 193, 195));
        panel.add(textField);
        panel.add(Box.createVerticalStrut(20));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Signup signUp = new Signup();
            signUp.setVisible(true);
        });
    }
}
