package org.example;

import org.example.controllers.SignUpController;
import org.example.services.UserService;
import org.example.views.SignIn;
import org.example.views.Signup;

public class Main {

    public static void main(String[] args) {
        // Create the view
        SignIn signIn = new SignIn();
        signIn.setVisible(true);
    }
}
