package org.example.views;

import org.example.controllers.SignInController;
import org.example.services.UserService;
import org.example.views.base.Layout;
import javax.swing.*;
import java.awt.*;

public class SignIn extends Layout {

    public JTextField nidText;
    public JPasswordField passwordText;
    public JButton signInButton;
    public JButton goToSignUpButton;
    private SignInController signInController;
    private UserService userService;

    public SignIn() {
        super("Sign In");
        setContentPane(createForm());
        userService = new UserService();
        signInController = new SignInController(this, userService);

    }

    private JPanel createForm() {
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(new Color(255, 255, 255));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Sign In", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(34, 193, 195));
        formPanel.add(titleLabel);
        formPanel.add(Box.createVerticalStrut(30));

        addLabelAndField(formPanel, "Nid:", nidText = new JTextField(20));
        addLabelAndField(formPanel, "Password:", passwordText = new JPasswordField(20));

        signInButton = new JButton("Log In");
        signInButton.setFont(new Font("Arial", Font.BOLD, 18));
        signInButton.setBackground(new Color(34, 193, 195));
        signInButton.setForeground(Color.WHITE);
        signInButton.setFocusPainted(false);
        signInButton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        formPanel.add(signInButton);

        // "Go to Sign-Up" button
        goToSignUpButton = new JButton("Not registered? Go to Sign-Up");
        goToSignUpButton.setFont(new Font("Arial", Font.PLAIN, 14));
        goToSignUpButton.setBackground(new Color(200, 200, 200));
        formPanel.add(goToSignUpButton);

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
            SignIn signIn = new SignIn();
            signIn.setVisible(true);
        });
    }
}
