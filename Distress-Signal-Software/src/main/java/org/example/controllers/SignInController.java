package org.example.controllers;

import org.example.models.User;
import org.example.services.UserService;
import org.example.services.session.UserSession;
import org.example.views.Home;
import org.example.views.SignIn;
import org.example.views.Signup;

import javax.swing.*;

public class SignInController {

    private SignIn view;
    private UserService userService;

    public SignInController(SignIn view, UserService userService) {
        this.view = view;
        this.userService = userService;

        // Add action listener for sign-in button
        view.signInButton.addActionListener(e -> handleSignIn());

        // Add action listener for "Go to Sign-Up" button
        view.goToSignUpButton.addActionListener(e -> handleGoToSignUp());
    }

    private void handleSignIn() {
        // Fetch data from the view
        String nid = view.nidText.getText();
        String password = new String(view.passwordText.getPassword());

        // Basic validation for empty fields
        if (nid.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Both fields must be filled!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Fetch user from the UserService
        User user = userService.getUserByNid(nid);

        if (user == null) {
            JOptionPane.showMessageDialog(view, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (!user.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(view, "Incorrect password!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(view, "Sign-in successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            UserSession.setLoggedInUser(user);
            handleGoToHome();
        }
    }

    private void handleGoToSignUp() {
        // Close the current Sign-In frame
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(view.signInButton);
        frame.dispose();

        // Open the Sign-Up frame
        Signup signUpView = new Signup();
        signUpView.setVisible(true);
    }

    private void handleGoToHome() {
        // Close the current Sign-In frame
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(view.signInButton);
        frame.dispose();

        // Open the Home frame
        Home homeView = new Home();
        homeView.setVisible(true);
    }
}
