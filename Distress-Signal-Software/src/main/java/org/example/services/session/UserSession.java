package org.example.services.session;



import org.example.models.User;

public class UserSession {
    private static User loggedInUser = null;

    // Get the logged-in user
    public static User getLoggedInUser() {
        return loggedInUser;
    }

    // Set the logged-in user
    public static void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    // Check if a user is logged in
    public static boolean isUserLoggedIn() {
        return loggedInUser != null;
    }

    // Log out the current user
    public static void logOut() {
        loggedInUser = null;
    }
}
