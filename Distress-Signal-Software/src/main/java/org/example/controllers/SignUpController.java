package org.example.controllers;

import org.example.models.User;
import org.example.services.UserService;
import org.example.views.SignIn;
import org.example.views.Signup;

import javax.swing.*;
import java.util.List;

public class SignUpController {

    private Signup view;
    private UserService userService;

    public SignUpController(Signup view, UserService userService) {
        this.view = view;
        this.userService = userService;

        // Add action listener for register button
        view.registerButton.addActionListener(e -> handleRegister());

        // Add action listener for "Go to Sign-In" button
        view.goToSignInButton.addActionListener(e -> handleGoToSignIn());
    }

    private void handleRegister() {
        // Fetch data from the view
        String nid = view.nidText.getText();
        String password = new String(view.passwordText.getPassword());
        String name = view.nameText.getText();
        String phone = view.phoneText.getText();
        String bloodGroup = view.bloodGroupText.getText();
        String sex = view.sexText.getText();
        String age = view.ageText.getText();
        String emergencyNumbers = view.emergencyNumbersText.getText();
        String emergencyMails = view.emergencyMailsText.getText();

        // Basic validation for empty fields
        if (nid.isEmpty() || password.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(view, "All required fields must be filled!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int ageInt = Integer.parseInt(age);

            // Create a new user
            User newUser = new User(nid, password, null, name, phone, bloodGroup, ageInt, sex,
                    List.of(emergencyNumbers.split(",")), List.of(emergencyMails.split(",")));

            // Check if user already exists
            if (userService.getUserByNid(nid) != null) {
                JOptionPane.showMessageDialog(view, "User already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Save the new user
            userService.addUser(newUser);

            JOptionPane.showMessageDialog(view, "User registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            handleGoToSignIn();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Invalid age format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void handleGoToSignIn() {
        // Close the current Sign-Up frame
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(view.registerButton);
        frame.dispose();

        // Open the Sign-In frame
        SignIn signInView = new SignIn();
        signInView.setVisible(true);
    }
}
