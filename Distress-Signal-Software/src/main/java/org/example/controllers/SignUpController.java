package org.example.controllers;

import org.example.models.User;
import org.example.services.UserService;
import org.example.views.SignIn;
import org.example.views.Signup;

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
        String nid = view.nidText.getText();  // Change from username to nid
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
            System.out.println("All required fields must be filled!");
            return;
        }

        // Create a new user
        User newUser = new User(nid, password, null, name, phone, bloodGroup, Integer.parseInt(age), sex,
                List.of(emergencyNumbers.split(",")), List.of(emergencyMails.split(",")));

        // Save the new user (calling the user service)
        userService.addUser(newUser);  // This method will print a success or failure message

        // Assuming addUser() prints success message internally, we just need to check for success
        // If user is added successfully, navigate to the sign-in page
        User addedUser = userService.getUserByNid(nid); // Retrieve the user by nid to check if it's in the list
        if (addedUser != null) {
            System.out.println("User registered successfully!");


            handleGoToSignIn();

        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }


    private void handleGoToSignIn() {
        // Close the current Sign-Up frame
        javax.swing.JFrame frame = (javax.swing.JFrame) javax.swing.SwingUtilities.getWindowAncestor(view.registerButton);
        frame.dispose();

        // Open the Sign-In frame
        SignIn signInView = new SignIn();
        signInView.setVisible(true);
    }
}
