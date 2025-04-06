package org.example.services;

import org.example.models.User;
import java.util.List;

public interface IUserService {
    void addUser(User user);
    User getUserByNid(String nid);
    void deleteUser(String nid);
    List<User> getAllUsers();
}
