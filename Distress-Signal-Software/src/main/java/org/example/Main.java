package org.example;

import org.example.utils.UserUtils;
import org.example.models.User;

public class Main {
    public static void main(String[] args) {
        // *Insert Users*
        UserUtils.addUser("nabilrafi", "nabilrafi112@gmail.com");
        UserUtils.addUser("john_doe", "john.doe@example.com");

        // *Retrieve a User*
        User user = UserUtils.getUserByUsername("nabilrafi");
        if (user != null) {
            System.out.println("User found: " + user);
        } else {
            System.out.println("User not found.");
        }

        // *Retrieve All Users*
        System.out.println("All Users: " + UserUtils.getAllUsers());

        // *Delete a User*
        UserUtils.deleteUser("john_doe");

        // *Retrieve All Users after Deletion*
        System.out.println("All Users After Deletion: " + UserUtils.getAllUsers());
    }
}