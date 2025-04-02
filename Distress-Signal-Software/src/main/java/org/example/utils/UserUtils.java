package org.example.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.models.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserUtils {
    private static final String FILE_PATH = "./database/users.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Read users from JSON file
    private static List<User> readUsers() {
        File file = new File(FILE_PATH);

        // Ensure the file exists; if not, create an empty one
        if (!file.exists()) {
            writeUsers(new ArrayList<>()); // Create an empty user.json file
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(FILE_PATH)) {
            Type userListType = new TypeToken<List<User>>() {}.getType();
            List<User> users = gson.fromJson(reader, userListType);

            // Ensure users is never null
            return (users != null) ? users : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Write users to JSON file
    private static void writeUsers(List<User> users) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // *1. Insert a new user*
    public static void addUser(String username, String email) {
        List<User> users = readUsers(); // Always returns a non-null list

        // Check if user already exists
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("User already exists!");
                return;
            }
        }

        users.add(new User(username, email));
        writeUsers(users);
        System.out.println("User added successfully!");
    }

    // *2. Retrieve a user by username*
    public static User getUserByUsername(String username) {
        List<User> users = readUsers();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    // *3. Delete a user by username*
    public static void deleteUser(String username) {
        List<User> users = readUsers();
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().getUsername().equals(username)) {
                iterator.remove();
                writeUsers(users);
                System.out.println("User deleted successfully!");
                return;
            }
        }
        System.out.println("User not found!");
    }

    // *4. Retrieve all users*
    public static List<User> getAllUsers() {
        return readUsers();
    }
}