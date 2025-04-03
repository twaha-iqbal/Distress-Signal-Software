package org.example.controllers;

import org.example.models.User;
import org.example.services.UserService;
import org.example.services.session.UserSession;
import org.example.views.Home;
import org.example.views.SignIn;
import org.example.views.Signup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        String nid = view.nidText.getText();  // Changed from username to nid
        String password = new String(view.passwordText.getPassword());

        // Basic validation for empty fields
        if (nid.isEmpty() || password.isEmpty()) {
            System.out.println("Both fields must be filled!");
            return;
        }

        // Fetch user from the UserService
        User user = userService.getUserByNid(nid);  // Changed to get user by nid

        if (user == null) {
            System.out.println("User not found!");
        } else if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password!");
        } else {
            System.out.println("Sign-in successful!");
            UserSession.setLoggedInUser(user);
            handleGoToHome();
            // Handle the successful sign-in (e.g., open the main app view)
        }
    }

    private void handleGoToSignUp() {
        // Close the current Sign-In frame
        javax.swing.JFrame frame = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(view.signInButton);
        frame.dispose();

        // Open the Sign-Up frame
        Signup signUpView = new Signup();
        signUpView.setVisible(true);

    }

    private void handleGoToHome() {
        // Close the current Sign-In frame
        javax.swing.JFrame frame = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(view.signInButton);
        frame.dispose();

        // Open the Sign-Up frame
        Home homeView = new Home();
        homeView.setVisible(true);
    }
}
