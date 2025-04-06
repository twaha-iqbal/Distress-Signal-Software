package org.example.views;

import org.example.controllers.SignUpController;
import org.example.services.UserService;
import org.example.views.base.Layout;

import javax.swing.*;
import java.awt.*;

public class Signup extends Layout {

    // Input fields for sign-up
    public JTextField nidText, nameText, phoneText, bloodGroupText, sexText, ageText, emergencyNumbersText, emergencyMailsText;
    public JPasswordField passwordText;
    public JButton registerButton, goToSignInButton;
    private SignUpController signUpController;
    private UserService userService;

    public Signup() {
        super("Sign Up");
        setContentPane(createForm());
        userService = new UserService();
        signUpController = new SignUpController(this, userService);
    }

    private JPanel createForm() {
        // Main panel for the form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS)); // Stack components vertically

        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20)); // Add top padding

        // Title label (Centered)
        JLabel titleLabel = new JLabel("Sign Up", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(34, 193, 195));
        formPanel.add(titleLabel);
        formPanel.add(Box.createVerticalStrut(30)); // Add some vertical space after the title

        // Add fields and labels with a two-column layout
        JPanel fieldsPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // 2 columns, with spacing between fields
        fieldsPanel.setOpaque(false); // Make the panel background transparent
        addLabelAndField(fieldsPanel, "NID:", nidText = new JTextField(20));
        addLabelAndField(fieldsPanel, "Password:", passwordText = new JPasswordField(20));
        addLabelAndField(fieldsPanel, "Name:", nameText = new JTextField(20));
        addLabelAndField(fieldsPanel, "Phone:", phoneText = new JTextField(20));
        addLabelAndField(fieldsPanel, "Blood Group:", bloodGroupText = new JTextField(20));
        addLabelAndField(fieldsPanel, "Sex:", sexText = new JTextField(20));
        addLabelAndField(fieldsPanel, "Age:", ageText = new JTextField(20));
        addLabelAndField(fieldsPanel, "Emergency Numbers:", emergencyNumbersText = new JTextField(20));
        addLabelAndField(fieldsPanel, "Emergency Emails:", emergencyMailsText = new JTextField(20));

        // Add the fields panel to the form
        formPanel.add(fieldsPanel);

        // Register button (Centered)
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 18));
        registerButton.setBackground(new Color(34, 193, 195));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        formPanel.add(registerButton);

        // "Go to Sign-In" button (Centered)
        goToSignInButton = new JButton("Already have an account? Go to Sign-In");
        goToSignInButton.setFont(new Font("Arial", Font.PLAIN, 14));
        goToSignInButton.setBackground(new Color(200, 200, 200));
        formPanel.add(goToSignInButton);

        // Center the form within the layout
        JPanel centeredPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centeredPanel.setOpaque(false);
        centeredPanel.add(formPanel);

        return centeredPanel;
    }

    private void addLabelAndField(JPanel panel, String labelText, JTextField textField) {
        // Label
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(new Color(70, 70, 70));

        // Text field
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setPreferredSize(new Dimension(250, 35));
        textField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        textField.setBackground(Color.WHITE);
        textField.setCaretColor(new Color(34, 193, 195));

        // Add label and text field to the panel
        panel.add(label);
        panel.add(textField);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Signup signUp = new Signup();
            signUp.setVisible(true);
        });
    }
}
