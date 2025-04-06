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

        userService = new UserService();
        setContentPane(createForm());
        signInController = new SignInController(this, userService);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 450);

        setLocationRelativeTo(null); // Center on screen


    }

    private JPanel createForm() {
        JPanel wrapper = new JPanel(new GridBagLayout());
        wrapper.setBackground(Color.WHITE);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.WHITE);
        formPanel.setPreferredSize(new Dimension(300, 300));

        JLabel titleLabel = new JLabel("Sign In");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(34, 193, 195));

        formPanel.add(titleLabel);
        formPanel.add(Box.createVerticalStrut(20));

        addLabelAndField(formPanel, "NID:", nidText = new JTextField(20));
        addLabelAndField(formPanel, "Password:", passwordText = new JPasswordField(20));

        signInButton = new JButton("Log In");
        styleButton(signInButton, new Color(34, 193, 195));
        formPanel.add(signInButton);
        formPanel.add(Box.createVerticalStrut(10));

        goToSignUpButton = new JButton("Not registered? Go to Sign-Up");
        goToSignUpButton.setFont(new Font("Arial", Font.PLAIN, 12));
        goToSignUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        goToSignUpButton.setBorderPainted(false);
        goToSignUpButton.setFocusPainted(false);
        goToSignUpButton.setContentAreaFilled(false);
        goToSignUpButton.setForeground(Color.GRAY);
        formPanel.add(goToSignUpButton);

        wrapper.add(formPanel);
        return wrapper;
    }

    private void addLabelAndField(JPanel panel, String labelText, JTextField textField) {
        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.setOpaque(false); // So it blends with the white background

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(new Color(70, 70, 70));
        label.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0)); // Space below label

        JPanel inputWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputWrapper.setOpaque(false); // Transparent background
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setPreferredSize(new Dimension(300, 35));
        textField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        textField.setBackground(Color.WHITE);
        textField.setCaretColor(new Color(34, 193, 195));
        inputWrapper.add(textField);

        fieldPanel.add(label, BorderLayout.NORTH);
        fieldPanel.add(inputWrapper, BorderLayout.CENTER);

        panel.add(fieldPanel);
        panel.add(Box.createVerticalStrut(20));
    }


    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SignIn signIn = new SignIn();
            signIn.setVisible(true);
        });
    }
}
