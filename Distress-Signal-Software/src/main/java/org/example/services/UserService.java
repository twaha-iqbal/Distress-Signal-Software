package org.example.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.models.User;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserService implements IUserService {

    private static final String FILE_PATH = "users.json";
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

    // Insert a new user
    public void addUser(User user) {
        List<User> users = readUsers(); // Always returns a non-null list

        // Check if user already exists by nid
        for (User existingUser : users) {
            if (existingUser.getNid().equals(user.getNid())) {  // Updated to nid
                System.out.println("User already exists!");
                return;
            }
        }

        users.add(user);
        writeUsers(users);
        System.out.println("User added successfully!");
    }

    // Retrieve a user by nid (National ID)
    public User getUserByNid(String nid) {  // Updated method name and parameter
        List<User> users = readUsers();

        for (User user : users) {
            if (user.getNid().equals(nid)) {  // Updated to nid
                return user;
            }
        }
        return null;
    }

    // Delete a user by nid
    public void deleteUser(String nid) {  // Updated to nid
        List<User> users = readUsers();
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().getNid().equals(nid)) {  // Updated to nid
                iterator.remove();
                writeUsers(users);
                System.out.println("User deleted successfully!");
                return;
            }
        }
        System.out.println("User not found!");
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        return readUsers();
    }
}
